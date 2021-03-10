package com.pantry.app.user.microserver.usermicroserver.model;

import com.pantry.app.user.microserver.usermicroserver.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;



@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Role role;


    /**------------------------Constructors------------------------**/

    /**
     * Default class constructor
     **/
    public User() {
    }

    /**
     * Class constructor specifying username and password.
     **/
    public User(String username, String password) {
        this.username = username;
        setPassword(password);
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     *
     **/

    public User(String username, String password, String email, String name) {
        this.username = username;
        setPassword(password);
        this.email = email;
        this.name = name;
    }

    public User(UserDTO userDTO) {
//        setId(userDTO.getId());
        setUsername(userDTO.getUsername());
        setPassword(userDTO.getPassword());
        setRole(new Role(userDTO.getRole()));
//        setEmail(userDTO.getEmail());
//        setName(userDTO.getName());
    }

    /**------------------------Getters and Setters------------------------**/

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

    // automatically encrypts the secret key
    public void setPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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


}
