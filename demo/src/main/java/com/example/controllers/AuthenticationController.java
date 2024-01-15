package com.example.controllers;

import com.example.data.UserRepository;
import com.example.models.User;
import com.example.models.dto.LoginFormDTO;
import com.example.models.dto.RegisterFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        // Optional<User> user = userRepository.findById(userId);
        // For some reason findById() isn't working with the valid
        // looking user ID (matches database). search all users
        // manually and compare ID

        for (User currentUser : userRepository.findAll()) {
            if (currentUser.getId() == userId) {
                return currentUser;
            }
        }
        return null;
//        if (user.isEmpty()) {
//            return null;
//        }
//
//        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Object> processRegistrationForm(@RequestBody @Valid RegisterFormDTO registerFormDTO,
                                                                   Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser.isPresent()) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        // TODO: The newUser.userDetails returned in the response is NULL, how does that end up populated??
        // We will need that on the front end to get the watchlists etc unless newUser.id is the thing to use
        // instead (which is populated)?
        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        Optional<User> theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser.isEmpty()) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.get().isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        setUserInSession(request.getSession(), theUser.get());

        return ResponseEntity.ok(theUser.get());
    }

// TODO: Can we just logout on the front end by clearing the cookie/session in axios?
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request){
//        request.getSession().invalidate();
//        return "redirect:/login";
//    }
}