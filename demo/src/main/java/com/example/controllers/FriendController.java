package com.example.controllers;

import com.example.data.FriendRepository;
import com.example.data.UserRepository;
import com.example.models.Friend;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/usersearch")
    public ResponseEntity<String> searchUser(@RequestParam String username) {

        Optional<User> user = userRepository.findByUsername(username);

        return ResponseEntity.ok(username);
    }

    @PostMapping("/addfriend")
    ResponseEntity<String> addFriend(@RequestParam("friendId") String friendId) {
        // Check if users exist

        User firstUser = userRepository.findByUsername(friendId).orElseThrow(() -> new RuntimeException("Sender not found"));
        User secondUser = userRepository.findByUsername(friendId).orElseThrow(() -> new RuntimeException("Receiver not found"));


        Friend friend = new Friend();

        if( !(friendRepository.existsByFirstUserAndSecondUser(firstUser,secondUser)) ){
        friend.setFirstUser(firstUser);
        friend.setSecondUser(secondUser);

        friendRepository.save(friend); }

        return ResponseEntity.ok("Friend added successfully");
    }

   /* @GetMapping("/friendlist")
    public ResponseEntity<List<Friend>> listFriends() {
        List<Friend> friends = (List<Friend>) friendRepository.findAll();
        return ResponseEntity.ok(friends);
    }*/

    @GetMapping("/friendlist")
    public List<Friend> getAllFriends() {
        return (List<Friend>) friendRepository.findAll();
    }
}
