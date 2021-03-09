package com.pantry.app.edgeservice.controller.impl;

import com.pantry.app.edgeservice.auth.security.AuthenticationRequest;
import com.pantry.app.edgeservice.auth.security.AuthenticationResponse;
import com.pantry.app.edgeservice.auth.security.JwtUtils;
import com.pantry.app.edgeservice.auth.security.MyUserDetailsService;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
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

    private static String userAuthOk;

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

        /*
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
         */
    }


    @RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerAndCreateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        if (userClient.alreadyExistsUserWithUsername(authenticationRequest.getUsername(), "Bearer " + getUserAuthOk())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        UserDTO user = new UserDTO(authenticationRequest.getUsername(),
                             authenticationRequest.getPassword());
        user.setRole(new RoleDTO("USER", user));

        userClient.add(user, "Bearer " + getUserAuthOk());

        return ResponseEntity.ok("User registered successfully!");
    }


    public static String getUserAuthOk() {
        return userAuthOk;
    }

    public static void setUserAuthOk(String userAuthOk) {
        AuthController.userAuthOk = userAuthOk;
    }

}
