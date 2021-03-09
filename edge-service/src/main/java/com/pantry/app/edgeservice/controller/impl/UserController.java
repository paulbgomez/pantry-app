package com.pantry.app.edgeservice.controller.impl;

import com.pantry.app.edgeservice.clients.UserClient;
import com.pantry.app.edgeservice.controller.interfaces.IUserController;
import com.pantry.app.edgeservice.dto.PantryDTO;
import com.pantry.app.edgeservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
public class UserController implements IUserController {

    @Autowired
    UserClient userClient;

    private static String userAuthOk;

    @GetMapping("user/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userClient.getUserById(id, "Bearer " + getUserAuthOk());
    }

    @GetMapping("/user/{username}")
    public UserDTO getUserByUsername(String username) {
        return userClient.getUserByUsername(username, "Bearer " + getUserAuthOk());
    }

    @PostMapping("user")
    public UserDTO add(UserDTO userDTO) {
        return userClient.add(userDTO, "Bearer " + getUserAuthOk());
    }

    @DeleteMapping("user/{id}")
    public void delete(Long id) {
        userClient.delete(id,"Bearer " + getUserAuthOk());
    }

    @PostMapping("user/new-pantry/{id}")
    public PantryDTO createPantry(PantryDTO pantryDTO, Long id) {
        return userClient.createPantry(pantryDTO, id, "Bearer " + getUserAuthOk());
    }

    @PutMapping("user/{id}")
    public void modify(Long id, UserDTO userDTO) {
        userClient.modify(id, userDTO, "Bearer " + getUserAuthOk());
    }

    @Override
    public boolean alreadyExistsUserWithEmail(String email) {
        return userClient.alreadyExistsUserWithEmail(email, "Bearer " + getUserAuthOk());
    }

    @Override
    public boolean alreadyExistsUserWithUsername(String username) {
        return userClient.alreadyExistsUserWithUsername(username, "Bearer " + getUserAuthOk());
    }

    public static String getUserAuthOk() {
        return userAuthOk;
    }

    public static void setUserAuthOk(String userAuthOk) {
        UserController.userAuthOk = userAuthOk;
    }
}
