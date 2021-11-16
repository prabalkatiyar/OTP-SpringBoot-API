package com.rapipay.OTPGeneration.OTPGeneration.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailServiceInterface {

	@Autowired

	private JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, int text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("vipulraj9839@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(Integer.toString(text));
		emailSender.send(message);

	}
}