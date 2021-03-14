package com.pantry.app.edgeservice.dto;


import javax.validation.constraints.NotNull;

public class UserDTO {

    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String email;
    private String name;
    private RoleDTO role;

    public UserDTO() {
    }

    public UserDTO(@NotNull String username,@NotNull  String password, RoleDTO role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(@NotNull String username, @NotNull String password, String email, String name, RoleDTO role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
