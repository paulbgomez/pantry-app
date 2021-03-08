package com.pantry.app.user.microserver.usermicroserver.controller.impl;

import com.pantry.app.user.microserver.usermicroserver.controller.interfaces.IUserController;
import com.pantry.app.user.microserver.usermicroserver.dto.PantryDTO;
import com.pantry.app.user.microserver.usermicroserver.dto.UserDTO;
import com.pantry.app.user.microserver.usermicroserver.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController implements IUserController {

    @Autowired
    IUserService userService;

    @GetMapping("user/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO add(@RequestBody @Valid UserDTO userDTO){
        return userService.add(userDTO);
    }

    @PutMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modify(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO){
        userService.update(id, userDTO);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @PostMapping("user/new-pantry/{id}")
    public PantryDTO createPantry(@RequestBody @Valid PantryDTO pantryDTO, @PathVariable Long id) {
        return userService.createPantry(pantryDTO, id);
    }
}
