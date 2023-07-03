package com.eg.utils;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

//Represented class as spring bean using @component
@Component
public class EmailUtils {
	// Java Predefined class JavaMailSender is an interface so implimentation
	// classwill be injected
	
	private Logger logger=LoggerFactory.getLogger(EmailUtils.class);
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String to, String subject, String body,File file) {
		boolean isMailSent = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);//true represent html content text in email 
			 //  helper.setCc(body);
			helper.addAttachment("His-Elig-Notice", file);//for attachment to mail 
			mailSender.send(mimeMessage);
			isMailSent=true;
		} catch (Exception e) {
			logger.error("Exception Occured",e);
		}
		return isMailSent;

	}

}
