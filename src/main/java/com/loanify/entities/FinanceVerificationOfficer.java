package com.loanify.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "finance_officer")
public class FinanceVerificationOfficer extends User {

	@Column
	private String finOfficerName;
	@Column
	private String finOfficerContact;

	public FinanceVerificationOfficer() {
		super();
	}

	

	public FinanceVerificationOfficer(String username, String password, String role, String finOfficerName,
			String finOfficerContact) {
		super(username, password, role);
		this.finOfficerName = finOfficerName;
		this.finOfficerContact = finOfficerContact;
	}



	public String getFinOfficerName() {
		return finOfficerName;
	}

	public void setFinOfficerName(String finOfficerName) {
		this.finOfficerName = finOfficerName;
	}

	public String getFinOfficerContact() {
		return finOfficerContact;
	}

	public void setFinOfficerContact(String finOfficerContact) {
		this.finOfficerContact = finOfficerContact;
	}

	@Override
	public String toString() {
		return "FinanceVerificationOfficer [finOfficerName=" + finOfficerName
				+ ", finOfficerContact=" + finOfficerContact + "]";
	}

}
