package com.loanify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanify.entities.EMI;

public interface IEmiRepository extends JpaRepository<EMI, Integer>{

}