package com.hrms.service;

import java.util.List;

import com.hrms.beans.AnnouncementBean;
import com.hrms.entity.CompanyAnnouncement;

public interface CompanyAnnouncementService {
	
	public AnnouncementBean saveAnnouncementDetails(CompanyAnnouncement companyannouncement);
	
    List<CompanyAnnouncement> Announcements();
	
	
}
	
	

	//public List<CompanyAnnouncement> Announcements(String startDate, String endDate);
	
//	public List<CompanyAnnouncement> getAllAnnouncements();
//	public CompanyAnnouncement getempById(Integer id);
//	public AnnouncementBean deleteannoun(int id);
	


