package com.example.demo.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name ="team_deatails")
public class TeamDetails {
	
	@Id
	@Column(name = "team_id",precision = 9)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamId;
	
	@Column(name = "team_name",nullable = false)
	private String teamName;
	
	@Column(name = "team_desc")
	private String teamDesc;
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	
	@Column(name = "update_date", nullable = false)
	private Date updateDate;
	
	@Column(name = "is_deleted")
	private Integer isDeleted;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "teamDetails", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Set<TeamRoles> teamRoles = new HashSet<>();
	
	

	public TeamDetails() {
		super();
	}

	
	public TeamDetails(Long teamId, String teamName, String teamDesc, Date createDate, Date updateDate,
			Integer isDeleted, User user, Set<TeamRoles> teamRoles) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamDesc = teamDesc;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.isDeleted = isDeleted;
		this.user = user;
		this.teamRoles = teamRoles;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamDesc() {
		return teamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public Set<TeamRoles> getTeamRoles() {
		return teamRoles;
	}

	public void setTeamRoles(Set<TeamRoles> teamRoles) {
		this.teamRoles = teamRoles;
	}

	@Override
	public String toString() {
		return "TeamDetails [teamId=" + teamId + ", teamName=" + teamName + ", teamDesc=" + teamDesc + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", isDeleted=" + isDeleted + ", user=" + user
				+ ", teamRoles=" + teamRoles + "]";
	}
	

}
