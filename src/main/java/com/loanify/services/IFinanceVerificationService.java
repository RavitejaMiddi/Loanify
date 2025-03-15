package com.loanify.services;

import java.util.List;

import com.loanify.entities.FinanceVerificationOfficer;

public interface IFinanceVerificationService  {
	

	public FinanceVerificationOfficer addFinanceOfficer(FinanceVerificationOfficer officer); 
	public FinanceVerificationOfficer getFinanceOfficer(int userId);
	public List<FinanceVerificationOfficer> getAllFinanceOfficers();
	

}
