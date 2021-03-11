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

    /**
     @USER ENDPOINTS
     **/

    /*
    GET REQUESTS
     */

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userClient.getUserById(id, "Bearer " + getUserAuthOk());
    }

    @GetMapping("/user/username/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userClient.getUserByUsername(username, "Bearer " + getUserAuthOk());
    }

    /*
    POST REQUESTS
     */

    @PostMapping("/user")
    public UserDTO add(@RequestBody UserDTO userDTO) {
        return userClient.add(userDTO, "Bearer " + getUserAuthOk());
    }

    @PostMapping("/user/new-pantry/{id}")
    public PantryDTO createPantry(@RequestBody @Valid PantryDTO pantryDTO, @PathVariable Long id) {
        return userClient.createPantry(pantryDTO, id, "Bearer " + getUserAuthOk());
    }

    /*
    PUT REQUESTS
     */

    @PutMapping("/user/{id}")
    public void modify(@PathVariable Long id, UserDTO userDTO) {
        userClient.modify(id, userDTO, "Bearer " + getUserAuthOk());
    }

    /*
    DELETE REQUESTS
     */

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userClient.delete(id,"Bearer " + getUserAuthOk());
    }

    public static String getUserAuthOk() {
        return userAuthOk;
    }

    public static void setUserAuthOk(String userAuthOk) {
        UserController.userAuthOk = userAuthOk;
    }
}
