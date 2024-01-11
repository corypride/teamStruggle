package com.example.models;

import jakarta.persistence.*;
import com.example.models.AbstractEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.lang.reflect.Array;

@Entity
public class User extends AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;
  
    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    @Valid
    private UserDetails userDetails;

    public User() {}

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    // Static method to use the bcrypt dependency for encoding
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Instance method to use the bcrypt multi-step matcher (.equals is not enough)
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}