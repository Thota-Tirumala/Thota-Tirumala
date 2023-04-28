package com.hrms.beans;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmployeeAttendanceRequest {
	
	private int empId;
	private Date startDate;
	private Date endDate;
	private String ipAddress;
	private String workFrom;
}
