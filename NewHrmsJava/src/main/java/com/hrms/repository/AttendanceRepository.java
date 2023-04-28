package com.hrms.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrms.entity.EmployeeAttendance;


public interface AttendanceRepository extends JpaRepository<EmployeeAttendance, Integer> {
	
	   List<EmployeeAttendance> findByEmpIdAndDateBetween(int empId, LocalDate startDate, LocalDate endDate);
	   
	   List<EmployeeAttendance> findByEmpId(int empId);
	   
	  // List<EmployeeAttendance> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
