package com.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.beans.JobHistoryResponse;
import com.hrms.entity.EmployeeJobHistory;
import com.hrms.repository.EmployeePersonalInfoRepository;

@Service
public class EmployeePersonalInfoServiceImpl implements EmployeePersonalInfoService {

//	private static Logger logger = Logger.class;

	@Autowired
	private EmployeePersonalInfoRepository employeePersonalInfoRepository;

	@Autowired
	private JobHistoryResponse historyResponse;

	@Override
	public JobHistoryResponse saveJobHistory(EmployeeJobHistory employeeJobHistory) {
//		logger.info("Entered into saveJobHistory()");

		EmployeeJobHistory ss = employeePersonalInfoRepository.save(employeeJobHistory);
		if (ss != null) {
			historyResponse.setMessage("Employee Job history add successfully");
			historyResponse.setStatus(true);
		} else {
			historyResponse.setMessage("Failed to save details ");
			historyResponse.setStatus(false);
		}
		return historyResponse;

	}

	@Override
	public EmployeeJobHistory getByPositionId(int positionId) {

		EmployeeJobHistory joblist = employeePersonalInfoRepository.getByPositionId(positionId);
		return joblist;
	}

	@Override
	public List<EmployeeJobHistory> getAllJobHistory() {

		return employeePersonalInfoRepository.findAll();
	}

	@Override
	public JobHistoryResponse deleteByPositionId(int positionId) {

		employeePersonalInfoRepository.deleteById(positionId);
		;
		historyResponse.setMessage("Employee Job history delete successfully");
		historyResponse.setStatus(true);
		return historyResponse;
	}

	@Override
	public EmployeeJobHistory updateJobHistory(int positionId, EmployeeJobHistory employeeJobHistory) {

		EmployeeJobHistory empjh = employeePersonalInfoRepository.getByPositionId(positionId);
		try {
			if (empjh != null) {
				
				empjh.setPositionId(employeeJobHistory.getPositionId());
				empjh.setPositionName(employeeJobHistory.getPositionName());
				empjh.setDepartmentName(employeeJobHistory.getDepartmentName());
				empjh.setJobTitleId(employeeJobHistory.getJobTitleId());
				empjh.setJobTitleName(employeeJobHistory.getJobTitleName());
				empjh.setClientName(employeeJobHistory.getClientName());
				empjh.setVendorName(employeeJobHistory.getVendorName());
				empjh.setAmountPaid(employeeJobHistory.getAmountPaid());
				empjh.setFromDate(employeeJobHistory.getFromDate());
				empjh.setToDate(employeeJobHistory.getToDate());

				return employeePersonalInfoRepository.save(empjh);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
