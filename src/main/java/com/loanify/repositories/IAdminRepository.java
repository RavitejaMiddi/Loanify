package com.loanify.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.loanify.entities.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer>{
		

	
}