package com.loanify.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User{
	
	@Column
	private String customerName;
	@Column
	private String mobileNumber;
	@Column
	private String emailId;
	@Column
	private LocalDate dateOfBirth;
	@Column
	private String gender;
	@Column
	private String nationality;
	@Column
	private String driversLicence; // dl
	@Column
	private String ssn; //ssn

	public Customer() {
		super();
	}


	

	public Customer(int userId, String username, String password, String role, String customerName, String mobileNumber,
			String emailId, LocalDate dateOfBirth, String gender, String nationality, String driversLicence,
			String ssn) {
		super(userId, username, password, role);
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.nationality = nationality;
		this.driversLicence = driversLicence;
		this.ssn = ssn;
	}
	
	


	public Customer(String username, String password, String role, String customerName, String mobileNumber,
			String emailId, LocalDate dateOfBirth, String gender, String nationality, String driversLicence,
			String ssn) {
		super(username, password, role);
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.nationality = nationality;
		this.driversLicence = driversLicence;
		this.ssn = ssn;
	}



	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getDriversLicence() {
		return driversLicence;
	}

	public void setDriversLicence(String driversLicence) {
		this.driversLicence = driversLicence;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerName='" + customerName + '\'' +
				", mobileNumber='" + mobileNumber + '\'' +
				", emailId='" + emailId + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", gender='" + gender + '\'' +
				", nationality='" + nationality + '\'' +
				", driversLicence='" + driversLicence + '\'' +
				", ssn='" + ssn + '\'' +
				'}';
	}
}
