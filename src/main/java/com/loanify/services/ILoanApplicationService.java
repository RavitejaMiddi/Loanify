package com.loanify.services;

import java.util.List;

import com.loanify.entities.LoanAgreement;
import com.loanify.entities.LoanApplication;
import com.loanify.exceptions.AdminApprovalException;
import com.loanify.exceptions.CustomerNotFoundException;
import com.loanify.exceptions.FinanceVerificationException;
import com.loanify.exceptions.LandVerificationException;
import com.loanify.exceptions.LoanAgreementNotFoundException;
import com.loanify.exceptions.LoanApplicationNotFoundException;

public interface ILoanApplicationService  {
	
	public LoanApplication addLoanApplication(int userId, double loanAppliedAmount,int loanTenureYears) throws CustomerNotFoundException;
	
	public LoanApplication updateLoanApplication(int loanApplicationId,LoanApplication loanApplication) throws LoanApplicationNotFoundException;
	public LoanApplication deleteLoanApplication(int loanApplicationId) throws LoanApplicationNotFoundException;
	public List<LoanApplication> retrieveAllLoanApplication();
	public LoanApplication retrieveLoanApplication(int loanApplicationId) throws LoanApplicationNotFoundException;
	public LoanApplication updateLandStatus(int loanApplicationId) throws LandVerificationException,LoanApplicationNotFoundException;
	public LoanApplication updateFinanceStatus(int loanApplicationId) throws FinanceVerificationException, LoanApplicationNotFoundException;
	public LoanApplication updateAdminStatus(int loanApplicationId) throws AdminApprovalException, LoanApplicationNotFoundException;
	public LoanAgreement getLoanAgreement(int loanApplicationId)throws LoanAgreementNotFoundException;
	

}
