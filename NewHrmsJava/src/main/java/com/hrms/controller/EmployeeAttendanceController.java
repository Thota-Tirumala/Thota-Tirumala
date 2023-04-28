package com.hrms.controller;

import java.text.ParseException;
import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.beans.EmployeeAttendanceRequest;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.service.EmployeeAttendanceService;
import com.hrms.util.IPAddress;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class EmployeeAttendanceController {
	
	   @Autowired
	   private EmployeeAttendanceService attendanceService;
	   
//	   @Autowired
//	   private AttendanceRepository attendanceRepo;
//	   
	   @PostMapping("/check-in/{empId}" )
	    public ResponseEntity<String> checkIn(@PathVariable int empId, @RequestBody EmployeeAttendanceRequest employeeAttendanceRequest) {
		   
		   
	        boolean isCheckedIn = attendanceService.checkIfCheckedInToday(empId);
	        if (isCheckedIn) {
            return ResponseEntity.badRequest().body("Employee has already checked in today");
//	        	return new ResponseEntity<EmployeeAttendance>(HttpStatus.BAD_REQUEST);
	        }
	        attendanceService.saveCheckInTime(empId,IPAddress.getCurrentIp(),employeeAttendanceRequest.getWorkFrom());
	        return ResponseEntity.ok("Employee checked in successfully");
				
	    }
	   
	   @PostMapping("/check-out/{empId}")
	    public ResponseEntity<String> checkOut(@PathVariable int empId) {
		   
	        attendanceService.saveCheckOutTime(empId);
	        return ResponseEntity.ok("Employee checked out successfully");
	    }
	   
	   @GetMapping("/employee/weekly/{empId}")
	    public ResponseEntity<List<EmployeeAttendance>> getEmployeeWeeklyAttendance(@PathVariable int empId,
	    		@QueryParam("startDate") String startDate,
	    		@QueryParam("endDate") String endDate) throws ParseException {
		   
//		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        List<EmployeeAttendance> attendanceList = attendanceService.getEmployeeWeeklyAttendance(empId, startDate, endDate);

	        return ResponseEntity.ok(attendanceList);
	    }
	   
	   
//	   @PostMapping("/saveEmployee")
//		public  EmployeeAttendancebean recordAttendance(@RequestBody EmployeeAttendance employeeattend){
//			
//			return attendanceService.saveAttendanceDetails(employeeattend);
//
//		}
	   
	   
}