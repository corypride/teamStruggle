package com.example.controllers;

import com.example.data.ReviewRepository;
import com.example.data.UserRepository;
import com.example.models.Review;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("addreview")
    /*public ResponseEntity<String> addReview(@PathVariable Integer userId,
                                            @PathVariable String content)*/
    ResponseEntity<String> addReview(@RequestParam("userId") User userId, @RequestBody Review review) {
      //add review stuff
        // Check if the user exists
        User user = userRepository.findById(userId.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        // Associate the review with the user
        review.setUser(user);

        // Save the review to the database
        reviewRepository.save(review);

        return ResponseEntity.ok("Review added successfully!");
    }

    @RequestMapping("listreviews")
    public String list(Model model) {

        model.addAttribute("reviews", reviewRepository.findAll());

        return "list";
    }
}
