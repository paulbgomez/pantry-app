package com.pantry.app.user.microserver.usermicroserver.dto;

import javax.validation.constraints.NotNull;

public class RoleDTO {

    @NotNull
    private String status;
    @NotNull
    private UserDTO userDTO;

    public RoleDTO() {
    }

    public RoleDTO(@NotNull String status, @NotNull UserDTO userDTO) {
        this.status = status;
        this.userDTO = userDTO;
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
