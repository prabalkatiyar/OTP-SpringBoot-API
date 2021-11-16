package com.rapipay.OTPGeneration.OTPGeneration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class OtpGenerationApplication {

	 private static Logger LOGGER = LogManager.getLogger(OtpGenerationApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(OtpGenerationApplication.class, args);
		LOGGER.info("Inside Main Class");
	}

}
