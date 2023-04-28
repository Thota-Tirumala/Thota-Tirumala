package com.hrms.beans;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class JobHistoryResponse {
	
	private String message;
	
	private boolean status;

}
