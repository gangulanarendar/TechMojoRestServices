package com.techmojo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bankrecord")
public class BankRecord {
	//BANK	IFSC	MICR	BRANCH	ADDRESS	CONTACT	CITY	DISTRICT	STATE
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(name="bank")
	private String bank;
	@Column(name="ifsc")
	private String ifsc;
	@Column(name="micr")
	private String micr;
	@Column(name="branch")
	private String branch;
	@Column(name="address")
	private String address;
	@Column(name="contact")
	private String contact;
	@Column(name="city")
	private String city;
	@Column(name="district")
	private String district;
	@Column(name="state")
	private String state;
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getMicr() {
		return micr;
	}
	public void setMicr(String micr) {
		this.micr = micr;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BankRecord [bank=" + bank + ", ifsc=" + ifsc + ", micr=" + micr + ", branch=" + branch + ", address="
				+ address + ", contact=" + contact + ", city=" + city + ", district=" + district + ", state=" + state
				+ "]";
	}
	
	
	

}
