package com.pantry.app.edgeservice.dto;

public class RoleDTO {

    private String status;
    private UserDTO userDTO;

    public RoleDTO() {
    }

    public RoleDTO(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
