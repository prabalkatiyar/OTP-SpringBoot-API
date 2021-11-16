package com.rapipay.OTPGeneration.OTPGeneration.Email;

public interface EmailServiceInterface {
	public void sendSimpleMessage(String to, String subject, int text);

}
