package com.example.demo.models.data;

import com.example.demo.models.User;


//finding a user by username and saving a user

import main.java.com.example.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
