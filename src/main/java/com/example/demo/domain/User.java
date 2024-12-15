package com.example.demo.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_details")
public class User {
	
	@Id
	@Column(name = "user_id",precision = 9)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="fullname",nullable = false, unique = true)
	private String fullName;
	
	@Column(name="username",nullable = false, unique = true)
	private String username;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "user_id_key")
	private String userIdKey;
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	
	@Column(name = "update_date", nullable = false)
	private Date updateDate;
	
	@Column(name = "is_deleted")
	private Integer isDeleted;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TeamDetails> teamDetals = new HashSet<>();
	
	
	
	public User(){}

	public User(Long userId, String fullName, String username, String password, String userIdKey, Date createDate,
			Date updateDate, Integer isDeleted, Set<Role> roles, Set<TeamDetails> teamDetals) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.userIdKey = userIdKey;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.isDeleted = isDeleted;
		this.roles = roles;
		this.teamDetals = teamDetals;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getUserIdKey() {
		return userIdKey;
	}

	public void setUserIdKey(String userIdKey) {
		this.userIdKey = userIdKey;
	}

	public Set<Role> getRolesList() {
	    Set<Role> roleNames = new HashSet<>();
	    // Assuming this.roles is a single Role object
	    if (this.roles != null) {
	    	roleNames = this.roles;
	    }

	    return roleNames;
	}
	
	
	public Set<TeamDetails> getTeamDetals() {
		return teamDetals;
	}

	public void setTeamDetals(Set<TeamDetails> teamDetals) {
		this.teamDetals = teamDetals;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", username=" + username + ", password=" + password
				+ ", userIdKey=" + userIdKey + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", isDeleted=" + isDeleted + ", roles=" + roles + ", teamDetals=" + teamDetals + "]";
	};
}
