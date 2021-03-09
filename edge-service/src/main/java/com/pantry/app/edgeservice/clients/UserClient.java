package com.pantry.app.edgeservice.clients;

import com.pantry.app.edgeservice.auth.security.AuthenticationRequest;
import com.pantry.app.edgeservice.dto.UserDTO;
import com.pantry.app.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/{username}")
    UserDTO getUserByUsername(@PathVariable(name = "username") String username);

}
