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
    public ResponseEntity<String> addReview(@PathVariable Integer userId,
                                            @PathVariable String content) {
      //add review stuff
        Review newReview = new Review();
        //idk what goes here
        newReview.setContent(content);
        reviewRepository.save(newReview);

        return ResponseEntity.ok("Review added successfully!");
    }

    @RequestMapping("listreviews")
    public String list(Model model) {

        model.addAttribute("reviews", reviewRepository.findAll());

        return "list";
    }
}
