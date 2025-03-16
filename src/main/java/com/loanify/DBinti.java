package com.loanify;

import java.time.LocalDate;

import com.loanify.entities.*;
import com.loanify.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


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

		@Autowired
				private ILoanAgreementRepository loanAgreementRepository;
		
		Logger logger = LoggerFactory.getLogger(DBinti.class);
		
	@Override
	public void run(String... args) throws Exception {
		EMI emi = new EMI(5, LocalDate.of(2030, 3, 16), 21247.044711268332, 1274822.6826761, 274822.6826760999);
		adminRepo.deleteAll();
		financeRepo.deleteAll();
		landverRepo.deleteAll();
		loanAppRepo.deleteAll();
		logger.info("Add data to the table");
		adminRepo.save(new Admin("ravi19", "ravi19", "admin", "Ravi Teja", "9403262005"));
//		landverRepo.save(new LandVerificationOfficer("nabeel123", "nabeel123", "landVerification", "Nabeel", "9988776655"));
		financeRepo.save(new FinanceVerificationOfficer("siva123", "siva123", "financeVerification", "siva Hariharan", "8899776644"));
		loanAppRepo.save(new LoanApplication(new Customer("customer1", "customer123", "customer", "customer1", "9988776655", "customer@gmail.com", LocalDate.of(1999, 8, 10), "male", "Indian", "336706642486", "AKPDQ6347D"), 1000000, 5));
       loanAgreementRepository.save( new LoanAgreement(6, 3, emi));
		logger.info("Data added to the table");
	}

}
