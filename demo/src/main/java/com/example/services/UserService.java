package com.example.services;

import com.example.data.UserRepository;
import com.example.models.User;
import com.example.models.dto.RegisterFormDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername (username);
    }

    public void registerNewUser(RegisterFormDTO registrationFormDTO) {
        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword());
        userRepository.save(newUser);
    }

    public void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }
}
