package com.maximus.crm.beans;

public class CreateAContactBean {

	private String firstName;
	private String lastName;
	private String middleName;
	private char gender;
	private String cellPhone;
	private String ssn;
	private String email;
	private int DOB;
	private String mediCareNum;
	private String medicAidNum;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDOB() {
		return DOB;
	}
	public void setDOB(int dOB) {
		DOB = dOB;
	}
	public String getMediCareNum() {
		return mediCareNum;
	}
	public void setMediCareNum(String mediCareNum) {
		this.mediCareNum = mediCareNum;
	}
	public String getMedicAidNum() {
		return medicAidNum;
	}
	public void setMedicAidNum(String medicAidNum) {
		this.medicAidNum = medicAidNum;
	}

}
