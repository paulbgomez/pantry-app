package com.pantry.app.user.microserver.usermicroserver.model;

import com.pantry.app.user.microserver.usermicroserver.dto.RoleDTO;
import com.pantry.app.user.microserver.usermicroserver.enums.Status;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    /**------------------------Constructors------------------------**/

    /**
     * Default class constructor
     **/
    public Role() {
    }

    /**
     * Class constructor specifying name and the user
     **/
    public Role(Status status, User user) {
        setStatus(status);
        this.user = user;
    }

    public Role(Status status){
        setStatus(status);
    }

    public Role(RoleDTO roleDTO) {
        setStatus(Status.valueOf(roleDTO.getStatus()));
        setUser(new User(roleDTO.getUserDTO()));
    }

    /**------------------------Getters and Setters------------------------**/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}