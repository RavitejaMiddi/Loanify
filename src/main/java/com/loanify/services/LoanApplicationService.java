package com.loanify.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanify.entities.Customer;
import com.loanify.entities.EMI;
import com.loanify.entities.LoanAgreement;
import com.loanify.entities.LoanApplication;
import com.loanify.entities.Status;
import com.loanify.exceptions.AdminApprovalException;
import com.loanify.exceptions.CustomerNotFoundException;
import com.loanify.exceptions.FinanceVerificationException;
import com.loanify.exceptions.LandVerificationException;
import com.loanify.exceptions.LoanAgreementNotFoundException;
import com.loanify.exceptions.LoanApplicationNotFoundException;
import com.loanify.repositories.ILoanApplicationRepository;

@Service
public class LoanApplicationService implements ILoanApplicationService {

	@Autowired
	ILoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IEmiService emiService;
	
	@Autowired
	ILoanAgreementService loanAgreementService;

	//To add any loan application or to apply loan
	@Override
	public LoanApplication addLoanApplication(int userId, double loanAppliedAmount,int loanTenureYears) throws CustomerNotFoundException {
		Customer customer = customerService.getCustomer(userId);
		LoanApplication loanApplication = new LoanApplication(customer,loanAppliedAmount,loanTenureYears);
		return loanApplicationRepository.save(loanApplication);
	}

	//To update any changes in loan application
	@Override
	public LoanApplication updateLoanApplication(int loanApplicationId, LoanApplication loanApplication) throws LoanApplicationNotFoundException {
		retrieveLoanApplication(loanApplicationId);
		return loanApplicationRepository.save(loanApplication);
	}

	//To delete any particular loan application by its id
	@Override
	public LoanApplication deleteLoanApplication(int loanApplicationId) throws LoanApplicationNotFoundException {
		LoanApplication loanApplication =retrieveLoanApplication(loanApplicationId);
		loanApplicationRepository.deleteById(loanApplicationId);
		return loanApplication;
	}
	
	//Get any loan application by its id
	@Override
	public LoanApplication retrieveLoanApplication(int loanApplicationId) throws LoanApplicationNotFoundException {
		return loanApplicationRepository.findById(loanApplicationId)
				.orElseThrow(() -> new LoanApplicationNotFoundException("Loan Application Not Found!!!"));
	}
	
	//Get all loan applications
	@Override
	public List<LoanApplication> retrieveAllLoanApplication() {
		return loanApplicationRepository.findAll();
	}
	
	//This is for track any application if it's status is approved
	@Override
	public LoanAgreement getLoanAgreement(int loanApplicationId) throws LoanAgreementNotFoundException {
		return loanAgreementService.getLoanAgreement(loanApplicationId);
	}
	
	//Land officer's verification and approval in any loan application
	@Override
	public LoanApplication updateLandStatus(int loanApplicationId) throws LandVerificationException, LoanApplicationNotFoundException{
		LoanApplication loanApplication = retrieveLoanApplication(loanApplicationId);
				
		if(loanApplication.getStatus()== Status.WAITING_FOR_LAND_VERIFICATION_OFFICE_APPROVAL 
						&& !loanApplication.isLandVerificationApproval() ) 
		{
			loanApplication.setLandVerificationApproval(true);
			loanApplication.setStatus(Status.WAITING_FOR_FINANCE_APPROVAL);
			return loanApplicationRepository.save(loanApplication);	
		}
		else  
		{
		 throw new LandVerificationException("Something went wrong") ;	
		}	
	}
	
	//Finance officer's verification and approval in any loan application
	@Override
	public LoanApplication updateFinanceStatus(int loanApplicationId) throws FinanceVerificationException, LoanApplicationNotFoundException {
		LoanApplication loanApplication = retrieveLoanApplication(loanApplicationId);
		
		if (loanApplication.getStatus() == Status.WAITING_FOR_FINANCE_APPROVAL
				&& loanApplication.isLandVerificationApproval()
				&& !loanApplication.isFinanceVerificationApproval()) {
			loanApplication.setFinanceVerificationApproval(true);
			loanApplication.setStatus(Status.PENDING);
			return loanApplicationRepository.save(loanApplication);

		} else 
			throw new FinanceVerificationException("Waiting for Land Verification Approval !!!");
	}
	
	//Admin's verification and approval in any loan application and also for add loan agreement
	@Override
	public LoanApplication updateAdminStatus(int loanApplicationId) throws AdminApprovalException, LoanApplicationNotFoundException {
		LoanApplication loanApplication = retrieveLoanApplication(loanApplicationId);
		
		if (loanApplication.getStatus() == Status.PENDING
				&& loanApplication.isLandVerificationApproval() 
				&& loanApplication.isFinanceVerificationApproval()) {
			loanApplication.setAdminApproval(true);
			loanApplication.setStatus(Status.APPROVED);
			
			EMI emi=emiService.addEmiDetails(loanApplication.getLoanAppliedAmount(), 10 , loanApplication.getLoanTenureYears());
			loanApplication.setLoanApprovedAmount(emi.getLoanAmount());
			
			loanAgreementService.addLoanAgreement(loanApplicationId, emi);
			
			return loanApplicationRepository.save(loanApplication);

		} else 
			throw new AdminApprovalException("Waiting for Officers Approval !!! ");
	}

}
