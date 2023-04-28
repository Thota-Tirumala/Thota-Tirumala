package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.entity.CompanyAnnouncement;

public interface AnnouncementRepo extends JpaRepository<CompanyAnnouncement, Integer> {

	//List<CompanyAnnouncement> findByDateBetween(LocalDate date1, LocalDate date2);

	@Query("FROM CompanyAnnouncement where startdate <= CURDATE() AND enddate >= CURDATE()")
	List<CompanyAnnouncement> getCurrentAnnouncementDetails();

}
