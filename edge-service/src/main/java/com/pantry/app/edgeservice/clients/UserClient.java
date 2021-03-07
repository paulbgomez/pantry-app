package com.pantry.app.edgeservice.clients;

import com.pantry.app.edgeservice.auth.security.AuthenticationRequest;
import com.pantry.app.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient("user-service")
public interface UserClient {


    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/account/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);


}
