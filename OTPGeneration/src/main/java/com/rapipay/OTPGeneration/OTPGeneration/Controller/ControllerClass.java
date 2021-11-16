package com.rapipay.OTPGeneration.OTPGeneration.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapipay.OTPGeneration.OTPGeneration.OtpGenerationApplication;
import com.rapipay.OTPGeneration.OTPGeneration.Entity.OTPEntity;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.InvalidEmailFormat;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.InvalidOTPException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.OTPExpireException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.OrderIDNotFoundException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.UserIDNotFoundException;
import com.rapipay.OTPGeneration.OTPGeneration.Service.ServiceInterface;

import java.util.Random;

@RestController
@RequestMapping()
public class ControllerClass {
	
	private static Logger LOGGER = LogManager.getLogger(OtpGenerationApplication.class);

	@Autowired
	ServiceInterface service;

	@PostMapping("/generateOTP/{userId}/{orderId}/{email}")
	public int generateOTP(@PathVariable String userId, @PathVariable String orderId, @PathVariable String email)
			throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException, InvalidEmailFormat {
		LOGGER.info("Inside generateOTP function of Controller Class");
		return service.generateOTP(userId, orderId, email);
	}

	@GetMapping("/validateOTP/{userId}/{orderId}/{otp}")
	public String validateOTP(@PathVariable String userId, @PathVariable String orderId, @PathVariable int otp)
			throws UserIDNotFoundException, OrderIDNotFoundException, InvalidOTPException, OTPExpireException {
		LOGGER.info("inside validateOTP in Controller Class");
		return service.validateOTP(userId, orderId, otp);
	}

}
