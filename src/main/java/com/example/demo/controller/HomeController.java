package com.example.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.config.LoggedInMember;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	
	 private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	 
	 @Autowired
	 private UserRepository userRepository; 
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder; 
	 
    @GetMapping("/")
    public String home() {
    	logger.info("/.....home....");
        return "home";
    }

    @GetMapping("/home")
    public String homepage() {
    	logger.info("/home....");
        return "home";
    }

    @GetMapping("/login")
    public String login(HttpServletResponse response) throws IOException {
    	logger.info("/login....");
		/*
		 * if(LoggedInMember.getUserDetails() != null) {
		 * response.sendRedirect("http://localhost:3000/"); }
		 */
        return "login";
    }
    
    @GetMapping("/logout")
    public String loginOut() {
    	logger.info("/logout....");
        return "redirect:login";
    }
    
    @PostMapping("/create-account")
    public String createAccount(@RequestParam("username")String username, @RequestParam("fullname") String fullName,@RequestParam("password")String password) {
    	logger.info("/createAccount....");
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(passwordEncoder.encode(password));
    	user.setFullName(fullName);
    	user.setCreateDate(new Date());
    	user.setUpdateDate(new Date());
    	user.setIsDeleted(0);
    	userRepository.save(user);
    	user.setUserIdKey(passwordEncoder.encode(String.valueOf(user.getUserId())));
    	Set<Role> userRoles = new HashSet<>();
    	Role role = new Role();
    	role.setRoleName("ROLE_USER");
    	role.setUser(user);
    	userRoles.add(role);
    	user.setRoles(userRoles);
    	userRepository.save(user);
        return "login";
    }
}

