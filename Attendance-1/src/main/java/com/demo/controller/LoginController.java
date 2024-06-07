package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.Service.AttendanceService;
import com.demo.Service.UserService;
import com.demo.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	 @Autowired
	    private UserService userService;

	    @GetMapping("/")
	    public String loginPage() {
	        return "login";
	    }

	    @PostMapping("/login")
	    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
	    	if(username.equals("admin") && password.equals("admin")) {
	    		List<User> users = userService.getAllUsers();
	            model.addAttribute("users", users);
	            return "admin_report";
	    	}
	    	else {
	        User user = userService.getUserByUsername(username);

	        if (user != null && user.getPassword().equals(password)) {
	            session.setAttribute("loggedInUser", username);
	            return "redirect:/home";
	        }

	        model.addAttribute("error", "Invalid username or password");
	        return "login";
	    }}

	    @GetMapping("/register")
	    public String registerPage() {
	        return "register";
	    }

	    @PostMapping("/register")
	    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String phoneNumber, @RequestParam String email, Model model) {
	        User existingUser = userService.getUserByUsername(username);
	        if (existingUser != null) {
	            model.addAttribute("error", "Username already exists");
	            return "register";
	        }

	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(password);
	        user.setPhoneNumber(phoneNumber);
	        user.setEmail(email);
	        userService.saveUser(user);

	        return "login";
	    }

	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/login";
	    }
	    
	   
	    
	
	}
