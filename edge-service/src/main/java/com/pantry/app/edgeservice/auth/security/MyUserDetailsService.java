package com.pantry.app.edgeservice.auth.security;

import com.pantry.app.edgeservice.clients.UserClient;
import com.pantry.app.edgeservice.controller.impl.AuthController;
import com.pantry.app.edgeservice.model.User;
import com.pantry.app.edgeservice.repository.UserRepository;
import com.opencsv.CSVReader;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userAuthOk = AuthController.getUserAuthOk();
        User user = userClient.getUserByUsername(username, userAuthOk);
        return new CustomUserDetails(user);
    }

    private void importAuthorizedServicesList(List<String> authorizedServices) {
        try (CSVReader reader = new CSVReader(new FileReader("authorizedServices/authorizedServicesList.csv"))) {
            List<String[]> lineInArray = reader.readAll();
            for (String[] line : lineInArray) {
                authorizedServices.add(line[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
