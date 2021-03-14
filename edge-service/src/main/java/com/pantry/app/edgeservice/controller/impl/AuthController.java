package com.pantry.app.edgeservice.controller.impl;

import com.pantry.app.edgeservice.auth.security.*;
import com.pantry.app.edgeservice.clients.UserClient;
import com.pantry.app.edgeservice.dto.RoleDTO;
import com.pantry.app.edgeservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    UserClient userClient;


    @RequestMapping(value = "/auth/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {

            throw new Exception("Invalid username or password", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerAndCreateToken(@RequestBody NewRegistrationRequest newRegistrationRequest) throws Exception {

        if (userClient.alreadyExistsUserWithUsername(newRegistrationRequest.getUsername(), "Bearer " + UserController.getUserAuthOk())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        RoleDTO roleDTO = new RoleDTO("USER");

        UserDTO user = new UserDTO(newRegistrationRequest.getUsername(),
                                    newRegistrationRequest.getPassword(),
                                    newRegistrationRequest.getEmail(),
                                    newRegistrationRequest.getName(),
                                    roleDTO);

        userClient.add(user, "Bearer " + UserController.getUserAuthOk());

        return ResponseEntity.ok("User registered successfully!");
    }


}
