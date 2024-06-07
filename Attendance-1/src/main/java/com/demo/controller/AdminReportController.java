package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.Service.AttendanceService;
import com.demo.Service.UserService;
import com.demo.model.Attendance;
import com.demo.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminReportController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/admin_report")
    public String adminReport(Model model) {
        if (!isAdmin()) {
            return "redirect:/login";
        }
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin_report";
    }

    @GetMapping("/user-report/{userId}")
    public String userReport(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        List<Attendance> attendances = attendanceService.getUserAttendance(userId);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("attendances", attendances);
        return "adminuser";
    }
    
    @PostMapping("/admin-logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/Back")
    public String back(Model model) {
    	List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin_report";
    }
    private boolean isAdmin() {
        String username = userService.getCurrentUsername();
        return "admin".equals(username);
    }
    
}