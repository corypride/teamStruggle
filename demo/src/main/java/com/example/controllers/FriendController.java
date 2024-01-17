package com.example.controllers;

import com.example.data.FriendRepository;
import com.example.data.UserRepository;
import com.example.models.Friend;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @PostMapping("/addfriend")
    ResponseEntity<String> addFriend(@PathVariable String username) {
        // Check if users exist

        Friend friend = new Friend();

        //

        friendRepository.save(friend);

        return ResponseEntity.ok("Friend added successfully");
    }

    @GetMapping("/friendlist")
    public List<Friend> getAllFriends() {
        return (List<Friend>) friendRepository.findAll();
    }
}
