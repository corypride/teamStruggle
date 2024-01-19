package com.example.controllers;

import com.example.data.FriendRepository;
import com.example.data.UserRepository;
import com.example.exceptions.ResourceNotFoundException;
import com.example.models.Friend;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @GetMapping("/usersearch")
    public ResponseEntity<String> searchUser(@RequestParam String username) {

        Optional<User> user = userRepository.findByUsername(username);

        return ResponseEntity.ok(username);
    }

    @PostMapping("/addfriend")
    ResponseEntity<String> addFriend(@RequestBody User user, @RequestParam("friendId") Integer friendId) {
        // Check if new friend user exist
        User newFriendUserObject = userRepository.findById(friendId)
                .orElseThrow(() -> new ResourceNotFoundException("No user with given id: " + friendId));

        //Check if already a friend
        //TODO: check the friendRepository to see if a friend object associated with this user and friendId exists

        //If no friend connection already exists,
        //Create a new friend object
        Friend newFriend = new Friend(user, friendId);

        //add friend to User's friend list
        user.addFriend(newFriend);

        //save new friend object and update user
        friendRepository.save(newFriend);
        userRepository.save(user);

        return ResponseEntity.ok("Friend added successfully");
    }

    //TODO: Refactor these controller mappings
    @GetMapping("friends/{userId}")
    public List<Friend> getFriendList(@PathVariable Integer userId) {

        //Find the user by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user with given id: " + userId));

        //return friends list
        return user.getFriends();
    }

    //TODO: Create a DELETE Mapping to remove friend

}