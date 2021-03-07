package com.pantry.app.user.microserver.usermicroserver.controller;

import com.pantry.app.user.microserver.usermicroserver.model.User;
import com.pantry.app.user.microserver.usermicroserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@PathVariable String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        if (userRepository.existsByUsername(username)){
            return userRepository.findByUsername(username).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader){
        if(userRepository.existsById(id)){
            return userRepository.getOne(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public User add(@RequestBody User user, @RequestHeader(value = "Authorization") String authorizationHeader){
        return userRepository.save(user);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader){
        if(userRepository.existsById(id)){
        User user = userRepository.getOne(id);
        userRepository.delete(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }
}
