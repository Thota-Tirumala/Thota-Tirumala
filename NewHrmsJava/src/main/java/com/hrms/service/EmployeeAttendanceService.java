package com.hrms.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.hrms.beans.EmployeeAttendanceRequest;
import com.hrms.beans.EmployeeAttendancebean;
import com.hrms.entity.EmployeeAttendance;


public interface EmployeeAttendanceService {
		
//	public EmployeeAttendancebean saveAttendanceDetails(EmployeeAttendance employeeattend);
	
	public boolean checkIfCheckedInToday(int empId);
	
	public void saveCheckInTime(int empId ,String ipAddress,String workFrom);
	
	public void saveCheckOutTime(int empId);
	
//	public EmployeeAttendance findByEmpId(int empId);
	
	public List<EmployeeAttendance> getEmployeeWeeklyAttendance(int empId, String startDate, String endDate);
		
	
}
