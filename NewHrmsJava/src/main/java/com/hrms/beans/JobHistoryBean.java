package com.hrms.beans;

import java.sql.Date;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class JobHistoryBean {

	private int id;
	private int positionId;
	private String positionName;
	private String departmentName;
	private int jobTitleId;
	private String jobTitleName;
	private String clientName;
	private String vendorName;
	private double amountPaid;
	private Date startDate;
	private Date endDate;
}
