package com.root.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long aid;
	private String name;
	
	@Column(unique=true)
	private String email;
	
	@Column(unique=true)
	private String phone;
	private String password;
	private List<String> emergency_codes;
	//Constructors
	public Admin() {
		
	}
	public Admin(String name, String email, String phone, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
	
	public Admin(long aid,String password) {
		this.aid=aid;
		this.password=password;
	}
	
	//Getters
	//We cannot get password in the info
	public long getAid() {
		return aid;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getPassword() {
		return password;
	}
	public List<String> getEmergency_codes() {
		return emergency_codes;
	}
	
	//Setters
	//Id is auto set and emergency codes are generated using a generator class in controller and are final 
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmergency_codes(List<String> emergency_codes) {
		this.emergency_codes = emergency_codes;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + "]";
	}
}