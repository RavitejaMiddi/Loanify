package com.loanify.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanify.entities.Customer;
import com.loanify.entities.LoanAgreement;
import com.loanify.entities.LoanApplication;
import com.loanify.entities.Status;
import com.loanify.entities.User;
import com.loanify.exceptions.CustomerNotFoundException;
import com.loanify.exceptions.LoanAgreementNotFoundException;
import com.loanify.exceptions.LoanApplicationNotFoundException;
import com.loanify.jwt.JwtTokenUtil;
import com.loanify.repositories.ICustomerRepository;
import com.loanify.services.CustomerService;
import com.loanify.services.IEmiService;
import com.loanify.services.ILoanAgreementService;
import com.loanify.services.ILoanApplicationService;

@RestController
@RequestMapping("/homeloan/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	Logger logger = Logger.getLogger(AdminController.class.getName());
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ICustomerRepository iCustomerRepository;
	
	@Autowired
	ILoanApplicationService loanapplicationservice;
	
	@Autowired
	IEmiService emiService;
	@Autowired
	ILoanAgreementService loanAgreementservice;
	
	boolean isLoggedIn = false;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	User user =null;
	
	
	
	public CustomerController() {
		logger.log(Level.INFO,"-----> Inside Customer Service Controller Working!");
	}
		
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		//user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
		
		return new ResponseEntity<>(customerService.addCustomer(customer),HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{userId}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable int userId,HttpServletRequest request) throws CustomerNotFoundException {
	   user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
		
		return new ResponseEntity<>(customerService.getCustomer(userId),HttpStatus.OK);
	}
	
	@GetMapping("/getUserId/{username}")
	public ResponseEntity<Integer> getUserId(@PathVariable String username) throws CustomerNotFoundException {
		return new ResponseEntity<>(customerService.getUserIdByUsername(username),HttpStatus.OK);
	}
	
	
	
	@PutMapping("/updateCustomer/{userId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int userId, @RequestBody Customer customer,HttpServletRequest request) throws CustomerNotFoundException {
		user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
		
		return new ResponseEntity<>(customerService.updateCustomer(userId,customer),HttpStatus.OK);
	}
	
	@PostMapping("/applyLoan/{userId}/{loanAppliedAmount}/{loanTenureYears}")
	public ResponseEntity<LoanApplication> applyLoan(@PathVariable int userId, @PathVariable double loanAppliedAmount, @PathVariable int loanTenureYears,HttpServletRequest request) throws CustomerNotFoundException {
		user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
		
		return new ResponseEntity<>(loanapplicationservice.addLoanApplication(userId,loanAppliedAmount,loanTenureYears), HttpStatus.OK);
	}
	
	@GetMapping("/loanTracker/{loanApplicationId}")
	public ResponseEntity<Status> loanTracker(@PathVariable int loanApplicationId,HttpServletRequest request) throws LoanApplicationNotFoundException{
		user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
		
		return new ResponseEntity<>(loanapplicationservice.retrieveLoanApplication(loanApplicationId).getStatus(),HttpStatus.OK);
	}
	
	@GetMapping("/loanAgreement/{loanApplicationId}")
	public ResponseEntity<LoanAgreement> getLoanAgreement(@PathVariable int loanApplicationId,HttpServletRequest request) throws LoanAgreementNotFoundException{
		user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
		System.out.println(loanApplicationId);
		return new ResponseEntity<>(loanapplicationservice.getLoanAgreement(loanApplicationId),HttpStatus.OK);
	}
	@GetMapping("/EMICalculator/{principal}/{intrestRate}/{tenure}")
	public ResponseEntity<?> emiCalculator(@PathVariable double principal,@PathVariable double intrestRate,@PathVariable int tenure){
		return new ResponseEntity<>(emiService.calculateEmi(principal, intrestRate, tenure),HttpStatus.OK);
	}
	
	@GetMapping("/loanAgreementId/{loanApplicationId}")
	public ResponseEntity<LoanAgreement> getLoanAgreementId(@PathVariable int loanApplicationId,HttpServletRequest request) throws LoanAgreementNotFoundException{
		user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
		//return new ResponseEntity <>(loanAgreementservice.getLoanAgreementId(loanApplicationId),HttpStatus.OK);
		LoanAgreement loanAgreementResponseEntity = loanAgreementservice.getLoanAgreement(loanApplicationId);
		System.out.println("response from loan agree : " + loanAgreementResponseEntity);
		return new ResponseEntity<>(loanAgreementservice.getLoanAgreement(loanApplicationId),HttpStatus.OK);
	}
	
	@GetMapping("/loanAgreement/{loanAgreementId}")
	public LoanAgreement getLoanAgreement(@PathVariable int loanAgreementId) throws LoanAgreementNotFoundException{
		//user=jwtTokenUtil.validateTokenAndGetUserDetails(request);
		return loanAgreementservice.getLoanAgreement(loanAgreementId);
		//return new ResponseEntity<>(loanAgreementservice.getLoanAgreement(loanApplicationId),HttpStatus.OK);
	}
}
