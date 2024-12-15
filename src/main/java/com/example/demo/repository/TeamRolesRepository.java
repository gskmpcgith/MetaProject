package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.TeamRoles;
import java.util.List;


public interface TeamRolesRepository extends JpaRepository<TeamRoles,Long>{
	TeamRoles findByTeamRoleId(Long teamRoleId);
	TeamRoles findByTeamRoleName(String teamRoleName);
}
