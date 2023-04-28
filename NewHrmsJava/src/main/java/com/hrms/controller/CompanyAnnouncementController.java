
package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.AnnouncementBean;
import com.hrms.entity.CompanyAnnouncement;
import com.hrms.service.CompanyAnnouncementServiceImpl;



@RestController
@RequestMapping("/announcement")
@CrossOrigin
public class CompanyAnnouncementController {

	@Autowired
	private CompanyAnnouncementServiceImpl companyannouncementservice;

	@PostMapping("/saveAnnouncemetdetails")
	public AnnouncementBean saveAnnouncementDetails(@RequestBody CompanyAnnouncement announcement) {

		return companyannouncementservice.saveAnnouncementDetails(announcement);
	}
	
	@GetMapping("/getAnnouncements")
	public List<CompanyAnnouncement> getAnnouncementsBetweendates()
	{
		List<CompanyAnnouncement> an =  companyannouncementservice.Announcements();
		System.out.println(an.get(0).getDescription());
		return an;
	}
 }

		
//	public List<CompanyAnnouncement> getAnnouncementsBetweenddates(){
//		
//		List<CompanyAnnouncement> an =  companyannouncementservice.Announcements();
//		
//		return an;
//}



//	@GetMapping("/getAnnouncements")
//	public List<CompanyAnnouncement> getAnnouncementsBetweenddates( @QueryParam("startDate")  String startDate,
//			@QueryParam("endDate")  Staring endDate)
//	{
//		List<CompanyAnnouncement> an =  companyannouncementservice.Announcements( startDate, endDate);
//		System.out.println(an.get(0).getDescription());
//		return an;
//	}
//}








//@GetMapping("/getAnnouncements")
//public List<CompanyAnnouncement> getAnnouncementsBetweenDates(
//        @RequestParam("startDate")  Date startDate,
//        @RequestParam("endDate") Date endDate) {
//    return companyannouncementservice.getAnnouncementsBetweenDates(startDate, endDate);


//	@GetMapping("/getAnnouncementDetails")
//	public ResponseEntity<List<CompanyAnnouncement>> getAnnouncementDetails(){
//		List<CompanyAnnouncement> details = companyannouncementservice.getAllAnnouncements();
//		return new ResponseEntity<> (details, HttpStatus.OK);
//	}
//	@GetMapping("/display/{id}")
//	public ResponseEntity<CompanyAnnouncement> getAnnouncementById(@PathVariable("id") int id){
//		CompanyAnnouncement byId = companyannouncementservice.getempById(id);
//		return new ResponseEntity<> (byId, HttpStatus.OK);
//}


//@DeleteMapping("/deleteById/{id}")
//public ResponseEntity<AnnouncementBean> deleteByid(@PathVariable("id") int id){
	
	
//	AnnouncementBean deleteannouncement = companyannouncementservice.deleteannoun(id);

	
//	return new ResponseEntity<>(deleteannouncement,HttpStatus.OK);
//}
//}

