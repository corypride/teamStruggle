package com.example.models.data;

import com.example.models.User;


//finding a user by username and saving a user
public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}