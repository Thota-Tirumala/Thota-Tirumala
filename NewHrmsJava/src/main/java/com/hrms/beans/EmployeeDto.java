package com.hrms.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	
    private Integer id;
	
	private String empId;
	
	private String firstName;
	
	private String lastName;
	
	private String dateOfBirth;
	
	private String empRole;
	
	private String qualification;
	
	private Long mobileNumber;
	
	private String email;
		
	private String gender;

}
