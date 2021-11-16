package com.rapipay.OTPGeneration.OTPGeneration.Service;

import com.rapipay.OTPGeneration.OTPGeneration.Exception.InvalidEmailFormat;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.InvalidOTPException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.OTPExpireException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.OrderIDNotFoundException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.UserIDNotFoundException;

public interface ServiceInterface {
	public int generateOTP(String userId, String orderId, String email)
			throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException, InvalidEmailFormat;

	public String validateOTP(String userId, String orderId, int otp)
			throws UserIDNotFoundException, OrderIDNotFoundException, InvalidOTPException, OTPExpireException;
	

}
