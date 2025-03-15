package com.loanify.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanify.entities.LandVerificationOfficer;
import com.loanify.repositories.ILandVerificationRepository;
import com.loanify.repositories.ILoanApplicationRepository;

@Service
public class LandVerificationService implements ILandVerificationService {

	@Autowired
	ILoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	ILandVerificationRepository iLandVerificationRepository;
	
	@Autowired
	ILoanApplicationService loanApplicationService;
	
	// saving a specific record by using the method save() of CrudRepository
	public LandVerificationOfficer addLandOfficer(LandVerificationOfficer officer) {
		iLandVerificationRepository.save(officer);
		return officer;
	}	
	

	@Override
	public LandVerificationOfficer getLandOfficer(int userId) {
		
		return iLandVerificationRepository.findById(userId).get();
	}

	@Override
	public List<LandVerificationOfficer> getAllLandOfficers() {
		return iLandVerificationRepository.findAll();
	}
}