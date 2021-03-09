package com.pantry.app.edgeservice.controller.impl;

import com.pantry.app.edgeservice.clients.UserClient;
import com.pantry.app.edgeservice.controller.interfaces.IUserController;
import com.pantry.app.edgeservice.dto.PantryDTO;
import com.pantry.app.edgeservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController implements IUserController {

    @Autowired
    UserClient userClient;

    private static String userAuthOk;

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userClient.getUserById(id, "Bearer " + getUserAuthOk());
    }

    @GetMapping("/user/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        System.out.println("user by username" + username + "header" + getUserAuthOk());
        return userClient.getUserByUsername(username, "Bearer " + getUserAuthOk());
    }

    @PostMapping("/user")
    public UserDTO add(@RequestBody UserDTO userDTO) {
        return userClient.add(userDTO, "Bearer " + getUserAuthOk());
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userClient.delete(id,"Bearer " + getUserAuthOk());
    }

    @PostMapping("/user/new-pantry/{id}")
    public PantryDTO createPantry(@RequestBody @Valid PantryDTO pantryDTO, @PathVariable Long id) {
        return userClient.createPantry(pantryDTO, id, "Bearer " + getUserAuthOk());
    }

    @PutMapping("/user/{id}")
    public void modify(@PathVariable Long id, UserDTO userDTO) {
        userClient.modify(id, userDTO, "Bearer " + getUserAuthOk());
    }

//    @GetMapping("/user/check-email/{email}")
//    public boolean alreadyExistsUserWithEmail(@PathVariable String email) {
//        return userClient.alreadyExistsUserWithEmail(email, "Bearer " + getUserAuthOk());
//    }
//
//    @GetMapping("/user/check-username/{username}")
//    public boolean alreadyExistsUserWithUsername(@PathVariable String username) {
//        System.out.println("username" + username);
//        return userClient.alreadyExistsUserWithUsername(username, "Bearer " + getUserAuthOk());
//    }

    public static String getUserAuthOk() {
        return userAuthOk;
    }

    public static void setUserAuthOk(String userAuthOk) {
        UserController.userAuthOk = userAuthOk;
    }
}
