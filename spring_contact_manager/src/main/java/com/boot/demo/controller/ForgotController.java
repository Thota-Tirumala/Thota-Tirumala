package com.boot.demo.controller;

import java.util.Random;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.demo.service.EmailService;

@RestController
public class ForgotController {
	
	Random random=new Random(1000);
	
	@Autowired
	private EmailService emailService;
	
	// email id form open handler
	@RequestMapping("/forgot")
	public ModelAndView openEmailForm()
	{
		
		return new ModelAndView("forgot_email_form.html");
	}
	
	@PostMapping("/send-otp")
	public ModelAndView sendOtp(@RequestParam("email") String email, HttpSession sesion)
	{
		System.out.println("email :"+email);
		
		//generate 4 digit otp 
		
		
		int otp=random.nextInt(9999);
		System.out.println("OTP :"+otp);
		
		//write  code for send otp
		String subject="OTP from SCM";
		String message ="<h1> OTP = "+otp+"</h1>";
		String to = email;
		
		boolean flag=this.emailService.sendEmail(subject, message, to);
		if (flag) {
			
			return new ModelAndView("verify_otp.html");
		}
		else {
			
			sesion.setAttribute("message", "check your email id !....");
			
			return new ModelAndView("forgot_email_form.html");
			
		}
	}
	
}
