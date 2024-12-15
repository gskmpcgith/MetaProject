package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.TeamDetails;


public interface TeamDetailsRepository extends JpaRepository<TeamDetails,Long> {
	
	TeamDetails findByTeamId(Long teamId);
	TeamDetails findByTeamName(String teamName);

}
