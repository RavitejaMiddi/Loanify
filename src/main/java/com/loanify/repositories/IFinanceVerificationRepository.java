package com.loanify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanify.entities.FinanceVerificationOfficer;

public interface IFinanceVerificationRepository extends JpaRepository<FinanceVerificationOfficer, Integer>{


	
}
