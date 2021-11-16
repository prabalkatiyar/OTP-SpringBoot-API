package com.rapipay.OTPGeneration.OTPGeneration.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rapipay.OTPGeneration.OTPGeneration.Exception.InvalidEmailFormat;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.OTPExpireException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.OrderIDNotFoundException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.UserIDNotFoundException;

@SpringBootTest
class ServiceTestClass {

	@Autowired
	ServiceInterface service;

	@Test
	@DisplayName("OTP Length")
	public void generateOTP1()
			throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException, InvalidEmailFormat {
		String expected = "";
		expected = String.valueOf(service.generateOTP("1", "11", "prabalkatiyarpk@gmail.com"));
		System.out.println(expected.length());
		assertTrue(expected.length() == 6);
	}

	@Test
	@DisplayName("OTP Format")
	public void generateOTP2()
			throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException, InvalidEmailFormat {
		String expected = "";
		expected = String.valueOf(service.generateOTP("1", "11", "prabalkatiyarpk@gmail.com"));
		assertTrue(expected.matches("[0-9]+"));
	}

	@Test
	@DisplayName("Email Validation1")
	public void emailValidation1() {
		String actual = "prabal@com";
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

		assertTrue(actual.matches(regex));
	}

	@Test
	@DisplayName("Email Validation2")
	public void emailValidation2() {
		String actual = "prabal.com";
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

		assertFalse(actual.matches(regex));
	}

}
