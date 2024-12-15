package com.example.demo.config;

import com.example.demo.domain.User;

public class LoggedInMember {
	
	private static Long userId;
	
	private static User user;
	
	public LoggedInMember() {};
	
	public LoggedInMember(User user) {
		this.userId = user.getUserId();
		this.user = user;
	}

	public static void setUser(User user) {
		LoggedInMember.user = user;
		LoggedInMember.userId = user.getUserId();
	};
	
	public static User getUserDetails() {
		return user;
	}
	
	public static Long getUserId() {
		return userId;
	}
}
