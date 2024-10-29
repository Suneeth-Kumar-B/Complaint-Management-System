package com.root.exceptions;

public class EmailUsernamePhoneAlreadyExists extends RuntimeException{
	public EmailUsernamePhoneAlreadyExists(String message) {
		super(message);
	}
}
