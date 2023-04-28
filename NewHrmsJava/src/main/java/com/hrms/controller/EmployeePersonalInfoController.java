package com.hrms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.beans.JobHistoryResponse;
import com.hrms.entity.EmployeeJobHistory;
import com.hrms.service.EmployeePersonalInfoService;


@RestController
public class EmployeePersonalInfoController {
	
	@Autowired
	private EmployeePersonalInfoService employeePersonalInfoService;

	// for saving job history details.
		@PostMapping("/saveJobHistory")
		public JobHistoryResponse saveJobHistory(@RequestBody EmployeeJobHistory employeeJobHistory) {
			
			return employeePersonalInfoService.saveJobHistory(employeeJobHistory);
			
		}
		
		// for display the particular details by usin id
		@GetMapping("/getDetails/{positionId}")
		public EmployeeJobHistory  getByPositionId(@PathVariable("positionId") int positionId)
		{
			return employeePersonalInfoService.getByPositionId(positionId);	
		}
		
		//for display the all employee history details
		@GetMapping("/getAllEmployeeHistory")
		public ResponseEntity<List<EmployeeJobHistory>> getAllEmployeeHistory()
		{
	
			List<EmployeeJobHistory> allejh =employeePersonalInfoService.getAllJobHistory();
			return new ResponseEntity<>(allejh,HttpStatus.OK);
		}
		
		// for delete the particular details by using id
		@DeleteMapping("delete/{positionId}")
		public JobHistoryResponse  deleteByPositionId(@PathVariable("positionId") int positionId)
		{
			return employeePersonalInfoService.deleteByPositionId(positionId);	
		}
		
		// for update the particular details by using id
		@PutMapping("/update/{positionId}")
		public ResponseEntity<EmployeeJobHistory> updateDetailsById(@PathVariable("positionId") int positionId,@RequestBody EmployeeJobHistory employeeJobHistory)
		{
			EmployeeJobHistory empJobHistory=employeePersonalInfoService.updateJobHistory(positionId, employeeJobHistory);
			
			return  ResponseEntity.ok(empJobHistory);
		}
}
