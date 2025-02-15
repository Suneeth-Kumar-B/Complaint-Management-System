package com.root.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExcpetionHandler extends Exception{
	@ExceptionHandler(EmailUsernamePhoneAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT) // Set appropriate HTTP status code
    public String handleUsernameAlreadyExistsException(EmailUsernamePhoneAlreadyExists ex) {
        return ex.getMessage();
    }
}
