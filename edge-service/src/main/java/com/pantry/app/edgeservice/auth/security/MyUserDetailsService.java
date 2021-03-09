package com.pantry.app.edgeservice.auth.security;

import com.opencsv.CSVReader;
import com.pantry.app.edgeservice.clients.UserClient;
import com.pantry.app.edgeservice.controller.impl.AuthController;
import com.pantry.app.edgeservice.controller.impl.UserController;
import com.pantry.app.edgeservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserClient userClient;

    @Autowired
    UserController userController;

    @Override
    public UserDetails loadUserByUsername(String username){
        UserDTO userDTO = userClient.getUserByUsername(username, "Bearer " + UserController.getUserAuthOk());
        return new CustomUserDetails(userDTO);
    }

//    private void importAuthorizedServicesList(List<String> authorizedServices) {
//        try (CSVReader reader = new CSVReader(new FileReader("authorizedServices/authorizedServicesList.csv"))) {
//            List<String[]> lineInArray = reader.readAll();
//            for (String[] line : lineInArray) {
//                authorizedServices.add(line[0]);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
