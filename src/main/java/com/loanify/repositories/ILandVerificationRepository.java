package com.loanify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanify.entities.LandVerificationOfficer;

public interface ILandVerificationRepository extends JpaRepository<LandVerificationOfficer, Integer> {

	
}


