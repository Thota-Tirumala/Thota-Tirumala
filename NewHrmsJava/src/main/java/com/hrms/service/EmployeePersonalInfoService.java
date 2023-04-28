package com.hrms.service;

import java.util.List;

import com.hrms.beans.JobHistoryResponse;
import com.hrms.entity.EmployeeJobHistory;

public interface EmployeePersonalInfoService {

	// save employee job history details 
 public JobHistoryResponse saveJobHistory(EmployeeJobHistory employeeJobHistory);
 
 	//get the job history details by use positionId
  public EmployeeJobHistory getByPositionId(int positionId);
  
  // get the all employee history details
  public List<EmployeeJobHistory> getAllJobHistory();
	
  // delete the job history details by use positionId
  public JobHistoryResponse deleteByPositionId(int positionId);
  
  // update the job history details by use positionId
  public EmployeeJobHistory updateJobHistory(int positionId,EmployeeJobHistory employeeJobHistory);

  
}
