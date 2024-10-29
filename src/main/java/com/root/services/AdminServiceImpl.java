package com.root.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.root.dao.AdminDAOImpl;
import com.root.exceptions.EmailUsernamePhoneAlreadyExists;
import com.root.generators.EmergencyCodeGenerator;
import com.root.models.Admin;

@Service
public class AdminServiceImpl implements AdminService {
	
	AdminDAOImpl adminDAO;
	EmergencyCodeGenerator emergencyCodeGenerator;
	
	AdminServiceImpl(AdminDAOImpl adminDAO, EmergencyCodeGenerator emergencyCodeGenerator){
		this.adminDAO = adminDAO;
		this.emergencyCodeGenerator = emergencyCodeGenerator;
	}

	@Override
	public void newAdmin(Admin admin) {
	    try {
	        adminDAO.newAdmin(admin);
	    } catch (DataIntegrityViolationException e) {
	    	String root[] = e.getCause().getMessage().split(" ");
	        if (root.length>0) {
	            throw new EmailUsernamePhoneAlreadyExists("Username/Email/Phone already exists: "+root[6]);
	        }
	        else throw e;
	    }
	}

	@Override
	public Admin validateAdmin(long aid, String password) {
		Admin admin = new Admin(aid, password);
		return adminDAO.validateAdmin(admin);
	}

}
