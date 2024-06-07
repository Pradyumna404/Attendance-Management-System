package com.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.UserRepository;
import com.demo.model.User;

import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final HttpSession session;

    public UserService(UserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User registerUser(User user) {
        return userRepository.save(user);
    }

   
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String getCurrentUsername() {
        return (String) session.getAttribute("loggedInUser");
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
