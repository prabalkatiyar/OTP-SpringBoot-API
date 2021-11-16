package com.rapipay.OTPGeneration.OTPGeneration.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UserIDNotFoundException extends Exception{
	

	private static final long serialVersionUID = 1L;

	public UserIDNotFoundException(String errorMessage)
	{
		super(errorMessage);
		System.out.println(errorMessage);
	}

}
