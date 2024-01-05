package com.example.controllers;

import com.example.models.User;
import com.example.models.dto.LoginFormDTO;
import com.example.models.dto.RegisterFormDTO;
import com.example.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registrationFormDTO,
                                          Errors errors) {

        if (errors.hasErrors()) {
            return "register";
        }

        Optional<User> existingUser = userService.findByUsername(registrationFormDTO.getUsername());

        if (existingUser.isPresent()) {
            errors.rejectValue("username", "username.alreadyExists", "A user with that username already exists");
            return "register";
        }

        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return "register";
        }

        userService.registerNewUser(registrationFormDTO);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors,
                                   HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }

        Optional<User> theUser = userService.findByUsername(loginFormDTO.getUsername());

        String password = loginFormDTO.getPassword();

        if (theUser.isEmpty() || !theUser.get().isMatchingPassword(password)) {
            errors.rejectValue(
                    "password",
                    "login.invalid",
                    "Credentials invalid. Please try again with the correct username/password combination."
            );
            return "login";
        }
        HttpSession session = (HttpSession) request.getSession();
        userService.setUserInSession(session, theUser.get());
        return "redirect:/home"; // Redirect to a secure page
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user");
        if (userId == null) {
            return null;
        }

        Optional<User> userOpt = userService.findByUsername(userId.toString());

        if (userOpt.isEmpty()) {
            return null;
        }

        return userOpt.get();
    }
}
