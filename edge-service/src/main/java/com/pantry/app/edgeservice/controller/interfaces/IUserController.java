package com.pantry.app.edgeservice.controller.interfaces;

import com.pantry.app.edgeservice.dto.PantryDTO;
import com.pantry.app.edgeservice.dto.UserDTO;

public interface IUserController {

    UserDTO getUserById(Long id);
    UserDTO getUserByUsername(String username);
    UserDTO add(UserDTO userDTO);
    void delete(Long id);
    PantryDTO createPantry(PantryDTO pantryDTO, Long id);
    void modify(Long id, UserDTO userDTO);
/*    boolean alreadyExistsUserWithEmail(String email);
    boolean alreadyExistsUserWithUsername(String username);*/

}
