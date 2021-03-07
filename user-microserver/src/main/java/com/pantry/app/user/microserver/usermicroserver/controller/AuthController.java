//package com.pantry.app.user.microserver.usermicroserver.controller;
//
//
//import com.pantry.app.user.microserver.usermicroserver.auth.dto.AuthenticationRequest;
//import com.pantry.app.user.microserver.usermicroserver.auth.dto.AuthenticationResponse;
//import com.pantry.app.user.microserver.usermicroserver.auth.service.MyUserDetailsService;
//import com.pantry.app.user.microserver.usermicroserver.auth.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtTokenUtil;
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    private static String userAuthOk;
//
//    /** AUTHENTICATION **/
//    @PostMapping("/user/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        }
//        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }
//
//    public static String getUserAuthOk() {
//        return userAuthOk;
//    }
//
//    public static void setUserAuthOk(String userAuthOk) {
//        AuthController.userAuthOk = userAuthOk;
//    }
//
//}
