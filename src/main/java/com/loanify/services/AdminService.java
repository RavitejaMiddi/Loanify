package com.loanify.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanify.entities.Admin;
import com.loanify.repositories.IAdminRepository;
import com.loanify.repositories.ILoanApplicationRepository;

@Service
public class AdminService implements IAdminService {

	@Autowired
	IAdminRepository iAdminRepository;
	
	@Autowired
	ILoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	ILoanApplicationService loanApplicationService;

	// saving a specific record by using the method save() of CrudRepository
	public Admin addAdmin(Admin admin) {
		iAdminRepository.save(admin);
		return admin;
	}

	// getting a specific record by using the method findById() of CrudRepository
	public Admin getAdmin(int userId) {
		return iAdminRepository.findById(userId).get();
	}

	// getting all Employee record by using the method findaAll() of CrudRepository
	public List<Admin> getAllAdmin() {
		return iAdminRepository.findAll();
	}
	
	
	
}