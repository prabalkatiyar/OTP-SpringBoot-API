package com.rapipay.OTPGeneration.OTPGeneration.Service;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapipay.OTPGeneration.OTPGeneration.OtpGenerationApplication;
import com.rapipay.OTPGeneration.OTPGeneration.DAO.OTPDao;
import com.rapipay.OTPGeneration.OTPGeneration.Email.EmailServiceImpl;
import com.rapipay.OTPGeneration.OTPGeneration.Email.EmailServiceInterface;
import com.rapipay.OTPGeneration.OTPGeneration.Entity.OTPEntity;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.InvalidEmailFormat;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.InvalidOTPException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.OTPExpireException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.OrderIDNotFoundException;
import com.rapipay.OTPGeneration.OTPGeneration.Exception.UserIDNotFoundException;

@Service
public class ServiceClass implements ServiceInterface {
	
	private static Logger LOGGER = LogManager.getLogger(OtpGenerationApplication.class);

	@Autowired
	OTPDao otpDao;

	@Autowired
	public EmailServiceInterface emailService;

	public int generateOTP(String userId, String orderId, String email)
			throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException, InvalidEmailFormat {
		
		LOGGER.info("Inside generateOTP function of Service Class");

		
		OTPEntity otpFind = otpDao.findById(orderId)
				.orElseThrow(() -> new OrderIDNotFoundException("Invalid OrderId : " + orderId));
		if (otpFind.getUser_id().equals(userId)) {
			validateEmail(email);
			int otp = createOTP();
			OTPEntity otpObject = new OTPEntity(userId, orderId, otp);
			otpDao.save(otpObject);
			System.out.println(email);
			emailService.sendSimpleMessage(email.trim(), "OTP", otpObject.getOtp());

			Timer t = new Timer();
			TimerTask tt = new TimerTask() {
				@Override
				public void run() {
					otpFind.setOtp(0);
					otpDao.save(otpFind);
				};
			};
			t.schedule(tt, 60000);

			return otp;
		} else {
			throw new UserIDNotFoundException("Invalid UserId : " + userId);
		}

	}

	public String validateOTP(String userId, String orderId, int otp)
			throws UserIDNotFoundException, OrderIDNotFoundException, InvalidOTPException, OTPExpireException {

		LOGGER.info("Inside validateOTP function of Service Class");
		OTPEntity otpFind = otpDao.findById(orderId)
				.orElseThrow(() -> new OrderIDNotFoundException("Invalid OrderId : " + orderId));
		if (otpFind.getUser_id().equals(userId)) {
			if (otpFind.getOtp() == 0) {
				throw new OTPExpireException("OTP Expired");
			} else if (otpFind.getOtp() == otp) {
				return "OTP Matched";
			} else {
				throw new InvalidOTPException("Invalid OTP");

			}
		} else {
			throw new UserIDNotFoundException(userId);
		}

	}

	public static int createOTP() {

		LOGGER.info("Inside createOTP function of Service Class");
		int otp = 100000 + (new Random().nextInt(900000));
		return otp;
	}

	public static boolean validateEmail(String email) throws InvalidEmailFormat {

		LOGGER.info("Inside validateEmail function of Service Class");
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		if (email.matches(regex)) {
			return true;
		} else {
			throw new InvalidEmailFormat("Invalid email : " + email);
		}

	}

}
