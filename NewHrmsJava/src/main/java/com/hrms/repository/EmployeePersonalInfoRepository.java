package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrms.entity.EmployeeJobHistory;

public interface EmployeePersonalInfoRepository extends JpaRepository<EmployeeJobHistory, Integer> {

	public EmployeeJobHistory getByPositionId(int positionId);
	
//	public EmployeeJobHistory deleteByPositionId(int positionId);

	//public int updateEmployeeJobHistory(EmployeeJobHistory employeeJobHistory);
	

}
