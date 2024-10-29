package com.root.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.root.dao.UserDAOImpl;
import com.root.exceptions.EmailUsernamePhoneAlreadyExists;
import com.root.models.User;

@Component
public class UserServiceImpl implements UserService{
	
	UserDAOImpl userDAO;
	
	UserServiceImpl(UserDAOImpl userDAO){
		this.userDAO=userDAO;
	}

	@Override
	public void newUser(User user) {
	    try {
	        userDAO.newUser(user);
	    } catch (DataIntegrityViolationException e) {
	    	String root[] = e.getCause().getMessage().split(" ");
	        if (root.length>0) {
	            throw new EmailUsernamePhoneAlreadyExists("Username/Email/Phone already exists: "+root[6]);
	        }
	        else throw e;
	    }
	}


	@Override
	public User validateUser(String username, String password) {
		User user = new User(username, password);
		return userDAO.validateUser(user);
	}

}
