package com.hrms.beans;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AnnouncementBean {
	
	private String message;
	
	private boolean status;
	
	
}
