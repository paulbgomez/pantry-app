package com.pantry.app.user.microserver.usermicroserver.auth.service;

import com.opencsv.CSVReader;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<String> authorizedServices = new ArrayList<>();
        importAuthorizedServicesList(authorizedServices);
        for (String service : authorizedServices) {
            if (service.equals(username)) {
                return new User(service, service,
                        new ArrayList<>());
            }
        }
        return null;
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
