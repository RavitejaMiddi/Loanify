package com.loanify.services;

import com.loanify.entities.EMI;

public interface IEmiService {

	public EMI addEmiDetails(double loanAppliedAmount,double intrestRate,int tenure); 
	public double calculateEmi(double principal,double intrestRate,int tenure);
	
}