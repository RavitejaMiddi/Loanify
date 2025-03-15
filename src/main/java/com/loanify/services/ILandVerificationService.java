package com.loanify.services;

import java.util.List;

import com.loanify.entities.LandVerificationOfficer;

public interface ILandVerificationService {
	

	public LandVerificationOfficer addLandOfficer(LandVerificationOfficer officer); 
	
	public LandVerificationOfficer getLandOfficer(int userId);
	public List<LandVerificationOfficer> getAllLandOfficers();
}


