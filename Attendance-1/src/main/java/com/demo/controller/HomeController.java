package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.Service.AttendanceService;
import com.demo.Service.UserService;
import com.demo.model.Attendance;
import com.demo.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	 @Autowired
	    private UserService userService;

	    @Autowired
	    private AttendanceService attendanceService;

	    @GetMapping("/home")
	    public String home(Model model, HttpSession session) {
	        String username = userService.getCurrentUsername();
	        if (username == null) {
	            return "redirect:/login";
	        }
	        User user = userService.getUserByUsername(username);
	        boolean signedIn = attendanceService.isSignedIn(user);

	        model.addAttribute("username", username);
	        model.addAttribute("signedIn", signedIn);
	        model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
	        session.removeAttribute("errorMessage");

	        return "home";
	    }

	    @PostMapping("/sign-in")
	    public String signIn(HttpSession session, Model model) {
	        String username = userService.getCurrentUsername();
	        User user = userService.getUserByUsername(username);
	        try {
	            attendanceService.signIn(user);
	        } catch (IllegalStateException e) {
	            session.setAttribute("errorMessage", "User has already signed in today");
	            return "redirect:/home";
	        }
	        session.setAttribute("hasSignedIn", true);
	        session.removeAttribute("hasSignedOut");
	        return "redirect:/home";
	    }

	    @PostMapping("/sign-out")
	    public String signOut(HttpSession session) {
	        String username = userService.getCurrentUsername();
	        User user = userService.getUserByUsername(username);
	        attendanceService.signOut(user);
	        session.setAttribute("hasSignedOut", true);
	        return "redirect:/home";
	    }

	    @GetMapping("/user-report")
	    public String userReport(Model model) {
	        String username = userService.getCurrentUsername();
	        User user = userService.getUserByUsername(username);
	        List<Attendance> attendances = attendanceService.getUserAttendance(user.getId());

	        model.addAttribute("attendances", attendances);

	        return "user_report";
	    }
	    
	    @PostMapping("/logout3")
	    public String logout() {
	    	return "login";
	    }
}
