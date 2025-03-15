package com.loanify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loanify.entities.Customer;
import com.loanify.entities.User;


public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("SELECT u from User u WHERE u.username=:username ")
	public User findByUsername(@Param("username") String username);

	
}
