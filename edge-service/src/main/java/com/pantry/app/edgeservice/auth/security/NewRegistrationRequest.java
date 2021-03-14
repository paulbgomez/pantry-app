package com.pantry.app.edgeservice.auth.security;

import java.io.Serializable;

public class NewRegistrationRequest implements Serializable {

    private String username;
    private String password;
    private String email;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //need default constructor for JSON Parsing
    public NewRegistrationRequest() {}

    public NewRegistrationRequest(String username, String password, String email) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setName(name);
    }
}
