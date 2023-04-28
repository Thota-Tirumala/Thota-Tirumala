package com.hrms.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.EmployeeDetails;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Integer>{

	
	
	//@Query("SELECT empId,firstName,dateOfBirth,lastName FROM EmployeeDetails where ")
	//List<Object[]> findEmployeeDetails();
	

	
	Optional<EmployeeDetails> findByEmailAndPassword(String email, String password);
	
	EmployeeDetails findByEmail(String email);
	
	EmployeeDetails findByFirstName(String firstName);
}
