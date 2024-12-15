package com.example.demo.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.LoggedInMember;
import com.example.demo.domain.TeamDetails;
import com.example.demo.domain.TeamRoles;
import com.example.demo.domain.User;
import com.example.demo.repository.TeamDetailsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CookieService;
import com.example.demo.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/meta/api")
//@CrossOrigin(origins = "http://localhost:3000")
public class MetaRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(MetaRestController.class);
	
	@Autowired
	private ObjectMapper objectMapper; 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CookieService cookieService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TeamDetailsRepository teamDetailsRepository;
	
	@GetMapping(value="/endpoint", produces = "application/json")
    public Boolean getCookie(HttpServletRequest request,@CookieValue("member-user") String cookieValue) {
		Boolean isTrue = false;
		logger.info(cookieValue+"*/MetaRestController*/ --> "+LoggedInMember.getUserId());
		if(LoggedInMember.getUserId() != null) {
			isTrue = true;
		}else if(LoggedInMember.getUserId() == null) {
			isTrue = cookieService.updateUserLoggedIn(request,cookieValue);
		}
		return isTrue;
    }
	
	@GetMapping(value="/user-key", produces = "application/json")
	public String getUserByKey(@RequestParam("userKey") String userKey) throws JsonProcessingException{
		Map<String,Object> jsonObject = new HashMap<>();
		User user = userRepository.findByUserIdKey(userKey);
		if(user != null) {
			jsonObject.put("isObj", true);
			jsonObject.put("user", user);
		}else {
			jsonObject.put("isObj", false);
		}
		String jsonString = objectMapper.writeValueAsString(jsonObject);
		return jsonString;
	}
	
	@GetMapping(value="/users-list", produces = "application/json")
	public List<User> getUsersList(HttpServletRequest request){
		TeamDetails team = new TeamDetails();
		team.setTeamName("Lucknow Royels");
		team.setUser(LoggedInMember.getUserDetails());
		team.setCreateDate(new Date());
		team.setUpdateDate(new Date());
		team.setIsDeleted(0);
		teamDetailsRepository.save(team);
		Set<TeamRoles> teamRoles = new HashSet<>();
		TeamRoles roles = new TeamRoles();
		roles.setTeamRoleName("ROLE_ADMIN");
		roles.setTeamDetails(team);
		teamRoles.add(roles);
		team.setTeamRoles(teamRoles);
		teamDetailsRepository.save(team);
		emailService.sendEmail("saik41472@gmail.com", null, null);
		List<User> list = userRepository.findAll();
		return list;
	}

}
