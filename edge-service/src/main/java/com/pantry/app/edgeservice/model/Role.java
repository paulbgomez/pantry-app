package com.pantry.app.edgeservice.model;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @Column(name = "user_id")
    private Long id;
    private String name; // TODO: cambiar a enum ADMIN, USER, THIRD_PARTY

    @OneToOne
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
    public Role(String name, User user) {
        this.name = name;
        this.user = user;
    }

    /**------------------------Getters and Setters------------------------**/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
