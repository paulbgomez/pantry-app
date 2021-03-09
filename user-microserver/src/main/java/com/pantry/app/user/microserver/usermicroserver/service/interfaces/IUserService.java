package com.pantry.app.user.microserver.usermicroserver.service.interfaces;

import com.pantry.app.user.microserver.usermicroserver.dto.PantryDTO;
import com.pantry.app.user.microserver.usermicroserver.dto.UserDTO;

public interface IUserService {

    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    UserDTO add(UserDTO userDTO);
    void delete(Long id);
    PantryDTO createPantry(PantryDTO pantryDTO, Long id);
    void update(Long id, UserDTO userDTO);
    boolean alreadyRegisteredEmail(String email);
    boolean alreadyRegisteredUsername(String username);
}
