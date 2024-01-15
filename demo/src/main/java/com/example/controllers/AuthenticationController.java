package com.example.controllers;

import com.example.data.UserRepository;
import com.example.models.User;
import com.example.models.dto.LoginFormDTO;
import com.example.models.dto.RegisterFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";

    @PostMapping("/register")
    public ResponseEntity<String> processRegistrationForm(@RequestBody @Valid RegisterFormDTO registerFormDTO,
                                                          Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>("Registration failed. Please check your input.", HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser.isPresent()) {
            return new ResponseEntity<>("A user with that username already exists", HttpStatus.BAD_REQUEST);
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> processLoginForm(@RequestBody @Valid LoginFormDTO loginFormDTO,
                                                 Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User theUser = optionalUser.get();

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        setUserInSession(request.getSession(), theUser);

        return new ResponseEntity<>(theUser, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }


    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }
    private void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }
}
