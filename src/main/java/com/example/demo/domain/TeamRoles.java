package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_roles")
public class TeamRoles {

	@Id
	@Column(name = "team_role_id", precision = 9)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamRoleId;

	@Column(name = "team_role_name", nullable = false)
	private String teamRoleName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id", nullable = false)
	private TeamDetails teamDetails;
	
	

	public TeamRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeamRoles(Long teamRoleId, String teamRoleName, TeamDetails teamDetails) {
		super();
		this.teamRoleId = teamRoleId;
		this.teamRoleName = teamRoleName;
		this.teamDetails = teamDetails;
	}

	public Long getTeamRoleId() {
		return teamRoleId;
	}

	public void setTeamRoleId(Long teamRoleId) {
		this.teamRoleId = teamRoleId;
	}

	public String getTeamRoleName() {
		return teamRoleName;
	}

	public void setTeamRoleName(String teamRoleName) {
		this.teamRoleName = teamRoleName;
	}

	public TeamDetails getTeamDetails() {
		return teamDetails;
	}

	public void setTeamDetails(TeamDetails teamDetails) {
		this.teamDetails = teamDetails;
	}

	@Override
	public String toString() {
		return "TeamRoles [teamRoleId=" + teamRoleId + ", teamRoleName=" + teamRoleName + ", teamDetails=" + teamDetails
				+ "]";
	}

}
