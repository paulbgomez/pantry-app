package com.pantry.app.edgeservice.clients;

import com.pantry.app.edgeservice.auth.security.AuthenticationRequest;
import com.pantry.app.edgeservice.dto.PantryDTO;
import com.pantry.app.edgeservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/username/{username}")
    UserDTO getUserByUsername(@PathVariable String username, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/user")
    UserDTO add(@RequestBody UserDTO userDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/user/check-email/{email}")
    boolean alreadyExistsUserWithEmail(@PathVariable String email, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/user/check-username/{username}")
    boolean alreadyExistsUserWithUsername(@PathVariable String username, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/user/{id}")
    UserDTO getUserById(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PutMapping("/user/{id}")
    void modify(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("/user/{id}")
    void delete(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/user/new-pantry/{id}")
    PantryDTO createPantry(@RequestBody @Valid PantryDTO pantryDTO, @PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/user/authenticate")
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

}
