package com.example.controllers;

import com.example.data.FriendRepository;
import com.example.data.UserRepository;
import com.example.models.Friend;
import com.example.models.User;
import jakarta.transaction.Transactional;
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
    public ResponseEntity<String> addFriend(@RequestParam("userId") Integer userId, @RequestParam("friendId") Integer friendId) {
        try {
            // Check if users exist
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            User friend = userRepository.findById(friendId).orElseThrow(() -> new RuntimeException("Friend not found"));

            // Check if they are not already friends
            if (user.getFriends().contains(friend)) {
                return ResponseEntity.badRequest().body("Users are already friends");
            }

            // Create friend relationships
            Friend friendRelationUser = new Friend();
            friendRelationUser.setUser(user);
            Friend friendRelationFriend = new Friend();
            friendRelationFriend.setUser(friend);

            // Save friend relationships to the database
            friendRepository.save(friendRelationUser);
            friendRepository.save(friendRelationFriend);

            // Save updated users to the database
            userRepository.save(user);
            userRepository.save(friend);


            return ResponseEntity.ok("Friend added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding friend: " + e.getMessage());
        }
    }


    @GetMapping("/friendlist")
    public List<Friend> getAllFriends() {
        return (List<Friend>) friendRepository.findAll();
    }
}
