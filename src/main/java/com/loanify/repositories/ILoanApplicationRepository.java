package com.loanify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanify.entities.LoanApplication;

public interface ILoanApplicationRepository extends JpaRepository<LoanApplication, Integer> {
	
}
