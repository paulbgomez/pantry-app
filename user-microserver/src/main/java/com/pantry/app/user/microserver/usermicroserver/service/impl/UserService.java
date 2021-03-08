package com.pantry.app.user.microserver.usermicroserver.service.impl;

import com.pantry.app.user.microserver.usermicroserver.clients.PantryClient;
import com.pantry.app.user.microserver.usermicroserver.dto.PantryDTO;
import com.pantry.app.user.microserver.usermicroserver.dto.UserDTO;
import com.pantry.app.user.microserver.usermicroserver.enums.Status;
import com.pantry.app.user.microserver.usermicroserver.model.Role;
import com.pantry.app.user.microserver.usermicroserver.model.User;
import com.pantry.app.user.microserver.usermicroserver.repository.UserRepository;
import com.pantry.app.user.microserver.usermicroserver.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PantryClient pantryClient;

    private User userExists(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
        }
    }

    public UserDTO findById(Long id) {
        return new UserDTO(userExists(id));
    }

    public UserDTO findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return new UserDTO(user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }

    public UserDTO add(UserDTO userDTO) {
       User user = new User(
               userDTO.getUsername(),
               userDTO.getPassword(),
               userDTO.getEmail(),
               userDTO.getName()
       );
       Role role = new Role(Status.USER, user);
       user.setRole(role);
       userRepository.save(user);
       return new UserDTO(user);
    }

    public void update(Long id, UserDTO userDTO) {
        User user = userExists(id);
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        userRepository.save(user);
    }

    public void delete(Long id) {
        User user = userExists(id);
        userRepository.delete(user);

    }

    public PantryDTO createPantry(PantryDTO pantryDTO, Long id) {
        User user = userExists(id);
       return pantryClient.add(pantryDTO, id);
    }
}
