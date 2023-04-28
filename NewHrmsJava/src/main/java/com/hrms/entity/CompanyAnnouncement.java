package com.hrms.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Company_Announcement")
@Component
public class CompanyAnnouncement {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int announid;

	@Column(name = "Announ_Subject", length = 100)
	private String subject;

	@Column(name = "Announ_description", length = 2000)
	private String description;
	
	@Column(name="announ_startdate")
	private String startdate;
	
	@Column(name="announ_enddate")
	private String enddate;
	
	public CompanyAnnouncement() {
		super();
		
	}
	
	public CompanyAnnouncement(int announid, String subject, String description, String startdate, String enddate) {
		super();
		this.announid = announid;
		this.subject = subject;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
	}



	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	
	public int getannounid() {
		return announid;
	}

	public void setannounid(int announid) {
		this.announid = announid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
