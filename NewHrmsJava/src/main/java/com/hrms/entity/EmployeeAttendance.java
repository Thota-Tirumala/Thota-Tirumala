package com.hrms.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="employee_attendance")
public class EmployeeAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "emp_id")
	private int empId;
	
	@Column(name = "ip_Address")
	private String ipAddress;
	
	private String workFrom;

	@Column(name = "in_time")
	private LocalTime checkInTime;

	@Column(name = "out_time")
	private LocalTime checkOutTime;

	@Column(name = "date")
	private LocalDate date;
	
	private String status;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private EmployeeDetails employee;

}
