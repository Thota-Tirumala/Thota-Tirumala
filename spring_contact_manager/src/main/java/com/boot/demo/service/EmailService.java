package com.boot.demo.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String subject,String message,String to)
	{
		//rest of the code 
		boolean f= false;
		String from= "techsoftindia2018@gmail.com";
		
		//variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties= System.getProperties();
		System.out.println("Properties :"+properties);
		
		//host set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//step-1
		Session session= Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("techsoftindia2018@gmail.com", "");
			}
		});
		
		session.setDebug(true);
		
		//step-2
		MimeMessage m = new MimeMessage(session);
		
		try {
			
			m.setFrom();
			
			m.addRecipient(Message.RecipientType.TO, new InternetAddress());
			
			m.setSubject(subject);
			
			m.setText(message);
			
			Transport.send(m);
			
			 System.out.println("sent success !....");
			 f=true;
			
		} catch (Exception e) {
			 e.printStackTrace();
			 
		}
		return f;

	}

}
