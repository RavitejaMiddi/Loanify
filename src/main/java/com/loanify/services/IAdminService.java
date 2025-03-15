package com.loanify.services;

import java.util.List;

import com.loanify.entities.Admin;

public interface IAdminService {

	public Admin addAdmin(Admin admin); 
	public Admin getAdmin(int userId);
	public List<Admin> getAllAdmin();
	
	}

