package com.loanify.exceptions;

public class LoanApplicationNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LoanApplicationNotFoundException() {
		super();
	}

	public LoanApplicationNotFoundException(String message) {

		super(message);
	}
}
