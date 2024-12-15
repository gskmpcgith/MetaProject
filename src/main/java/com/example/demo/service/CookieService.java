package com.example.demo.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.LoggedInMember;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CookieService {
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private UserRepository userRepository; 

    public boolean isUserLoggedIn(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("member-user".equals(cookie.getName())) {
                    // Check if the cookie has the expected value
                	String encodedValue = String.valueOf(cookie.getValue());
                	String expectedValue = String.valueOf(userRepository.findByUserIdKey(encodedValue).getUserId());
                    boolean cookieValue = passwordEncoder.matches(expectedValue,encodedValue);
                    // Replace "your-expected-value" with the value you expect to verify against
                    if (cookieValue) {
                        return true; // Cookie with expected value found
                    }
                }
            }
        }
        return false; // Cookie not found or value does not match
    }
    
    public boolean updateUserLoggedIn(HttpServletRequest request,String cookieValue) {
    	try {
	    	if(StringUtils.isNotBlank(cookieValue)) {
	    		User user = userRepository.findByUserIdKey(cookieValue);
	    		if(user != null) {
		    		LoggedInMember.setUser(user);
		    		return true;
	    		}
	    	}
    	}catch(Exception exe) {
    		
    	}
    	return false;
    }
}


