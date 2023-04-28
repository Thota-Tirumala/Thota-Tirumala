package com.hrms.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.beans.EmployeeAttendanceRequest;
import com.hrms.beans.EmployeeAttendancebean;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.repository.AttendanceRepository;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;

	@Autowired
	private EmployeeAttendancebean eab;

	@Autowired
	private EmployeeAttendanceRequest employeeRequest;

	@Override
	public boolean checkIfCheckedInToday(int empId) {

		List<EmployeeAttendance> employeeAttendance = attendanceRepo.findByEmpId(empId);

		if ( employeeAttendance.size() <= 0 ) {
//			eab.setMsg("Employee not found ! please enter valid id");
//			eab.setStatus(false);    
			return false;
		}
		else {
			LocalDate checkInTime = employeeAttendance.get(0).getDate();
			if (checkInTime != null && checkInTime.isEqual(LocalDate.now())) {
				eab.setStatus(true);
				return true;
			}
		}
		return false;
	}

	@Override
	public void saveCheckInTime(int empId, String ipAddress, String workFrom) {

		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		//		EmployeeAttendance employeeAttendance = attendanceRepo.findById(empId).orElse(null);
		employeeAttendance.setCheckInTime(LocalTime.now());
		employeeAttendance.setDate(LocalDate.now());
		employeeAttendance.setEmpId(empId);
		employeeAttendance.setIpAddress(ipAddress);
		employeeAttendance.setWorkFrom(workFrom);
		attendanceRepo.save(employeeAttendance);

		if(employeeAttendance != null)
		{
			employeeAttendance.setStatus("present");
			attendanceRepo.save(employeeAttendance);
		}
		else {

			employeeAttendance.setStatus("absent");
			attendanceRepo.save(employeeAttendance);
		}
	}

	@Override
	public void saveCheckOutTime(int empId) {

		List<EmployeeAttendance> employeeAttendanceList = attendanceRepo.findByEmpId(empId);

		EmployeeAttendance employeeAttendance = employeeAttendanceList.get(0);
		employeeAttendance.setCheckOutTime(LocalTime.now());
		employeeAttendance.setDate(LocalDate.now());
		employeeAttendance.setWorkFrom(employeeAttendance.getWorkFrom());
		EmployeeAttendance employeeAtt = attendanceRepo.save(employeeAttendance);
	}

	@Override
	public List<EmployeeAttendance> getEmployeeWeeklyAttendance(int empId, String startDate, String endDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			LocalDate date1 = sdf.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate date2 = sdf.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			return attendanceRepo.findByEmpIdAndDateBetween(empId, date1, date2);
		}
		catch(Exception e)
		{
			return null;	
		}
	}

	//	@Override
	//	public EmployeeAttendancebean saveAttendanceDetails(EmployeeAttendance employeeattend) {
	//
	//		EmployeeAttendance ss = attendanceRepo.save(employeeattend);
	//		if(ss !=null ) {
	//			eab.setMsg("Employee attendance details  saved Successfully");
	//			eab.setStatus(true);
	//		}else {
	//			eab.setMsg("failed !");
	//			eab.setStatus(false);
	//		}
	//		return eab;
	//	}

	//	@Override
	//	public EmployeeAttendance findByEmpId(int empId) {
	//
	//		Optional<EmployeeAttendance> empatt = attendanceRepo.findById(empId);
	//
	//		if(empatt.isPresent()) {
	//			return empatt.get();
	//		}
	//		else {
	//
	//			eab.setStatus(false);
	//			System.out.println("invalid details");
	//		}
	//
	//		return null ;
	//	}

}


