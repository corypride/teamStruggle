package com.example.demo.models.data;

import com.example.demo.models.User;


//finding a user by username and saving a user
public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}