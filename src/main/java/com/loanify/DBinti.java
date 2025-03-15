package com.loanify;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.loanify.entities.Admin;
import com.loanify.entities.Customer;
import com.loanify.entities.FinanceVerificationOfficer;
import com.loanify.entities.LandVerificationOfficer;
import com.loanify.entities.LoanApplication;
import com.loanify.repositories.IAdminRepository;
import com.loanify.repositories.ICustomerRepository;
import com.loanify.repositories.IFinanceVerificationRepository;
import com.loanify.repositories.ILandVerificationRepository;
import com.loanify.repositories.ILoanApplicationRepository;


@Component
public class DBinti implements CommandLineRunner {
	
		@Autowired
		private IAdminRepository adminRepo;
		
		@Autowired
		private ICustomerRepository custRepo;
		
		@Autowired
		private IFinanceVerificationRepository financeRepo;
		
		@Autowired
		private ILandVerificationRepository landverRepo;
		
		@Autowired
		private ILoanApplicationRepository loanAppRepo;
		
		Logger logger = LoggerFactory.getLogger(DBinti.class);
		
	@Override
	public void run(String... args) throws Exception {
		
		adminRepo.deleteAll();
		financeRepo.deleteAll();
		landverRepo.deleteAll();
		loanAppRepo.deleteAll();
		logger.info("Add data to the table");
		adminRepo.save(new Admin("raj123", "raj123", "admin", "Likesh", "7788996655"));
		landverRepo.save(new LandVerificationOfficer("nabeel123", "nabeel123", "landVerification", "Nabeel", "9988776655"));
		financeRepo.save(new FinanceVerificationOfficer("siva123", "siva123", "financeVerification", "siva Hariharan", "8899776644"));
		loanAppRepo.save(new LoanApplication(new Customer("customer1", "customer123", "customer", "customer1", "9988776655", "customer@gmail.com", LocalDate.of(1999, 8, 10), "male", "Indian", "336706642486", "AKPDQ6347D"), 1000000, 5));
       
		logger.info("Data added to the table");
	}

}
