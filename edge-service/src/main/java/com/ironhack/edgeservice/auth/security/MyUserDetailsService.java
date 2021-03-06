package com.ironhack.edgeservice.auth.security;

import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.repository.UserRepository;
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
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username "+username+" not found"));
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
