package com.hrms.beans;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PasswordChangeResponse {
	
    private String msg;
	
	private boolean status;

}
