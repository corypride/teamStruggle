package com.example.controllers;

import com.example.data.ReviewRepository;
import com.example.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @PostMapping("addreview")
    public ResponseEntity<?> addReview(@RequestBody Review review) {
      //add review stuff
        return ResponseEntity.ok("Added review successfully.");
    }

    @RequestMapping("listreviews")
    public String list(Model model) {

        model.addAttribute("reviews", reviewRepository.findAll());

        return "list";
    }
}
