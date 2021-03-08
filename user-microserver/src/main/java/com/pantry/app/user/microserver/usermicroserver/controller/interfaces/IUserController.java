package com.pantry.app.user.microserver.usermicroserver.controller.interfaces;

import com.pantry.app.user.microserver.usermicroserver.dto.PantryDTO;
import com.pantry.app.user.microserver.usermicroserver.dto.UserDTO;

public interface IUserController {

    UserDTO getUserById(Long id);
    UserDTO getUserByUsername(String username);
    UserDTO add(UserDTO userDTO);
    void delete(Long id);
    PantryDTO createPantry(PantryDTO pantryDTO, Long id);
    void modify(Long id, UserDTO userDTO);

}
