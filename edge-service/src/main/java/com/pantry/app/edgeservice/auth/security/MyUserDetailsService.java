package com.pantry.app.edgeservice.auth.security;

import com.pantry.app.edgeservice.clients.UserClient;
import com.pantry.app.edgeservice.controller.impl.UserController;
import com.pantry.app.edgeservice.dto.RoleDTO;
import com.pantry.app.edgeservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserClient userClient;


    @Override
    public UserDetails loadUserByUsername(String username){
        UserDTO userDTO = userClient.getUserByUsername(username, "Bearer " + UserController.getUserAuthOk());
        userDTO.setRole(new RoleDTO("USER"));
        return new CustomUserDetails(userDTO);
    }

}
