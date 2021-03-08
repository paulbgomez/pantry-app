package com.pantry.app.user.microserver.usermicroserver.controller.impl;

import com.pantry.app.user.microserver.usermicroserver.model.User;
import com.pantry.app.user.microserver.usermicroserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("user/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@PathVariable String username){
        if (userRepository.existsByUsername(username)){
            return userRepository.findByUsername(username).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public User add(@RequestBody User user){
        //TODO construir el rol desde servico y añadirlo al user
        return userRepository.save(user);
    }

    @PutMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User modify(@PathVariable Long id, @RequestBody User user){
        if(userRepository.existsById(id)){
            User oldUser = userRepository.findById(id).get();
            oldUser.setName(user.getName());
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setEmail(user.getEmail());
            oldUser.setRole(user.getRole());
            return userRepository.save(oldUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        if(userRepository.existsById(id)){
        User user = userRepository.getOne(id);
        userRepository.delete(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }
}
