package com.boot.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.boot.demo.dao.ContactRepository;
import com.boot.demo.dao.UserRepository;
import com.boot.demo.entities.Contact;
import com.boot.demo.entities.User;
import com.boot.demo.helper.Message;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	// method for addding common data to response
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("username" + userName);

		// get the user using the email
		User user = userRepository.getUserByUserName(userName);
		System.out.println("User " + user);
		model.addAttribute("user", user);

	}

	// dashboard home
	@RequestMapping("/index")
	public ModelAndView dashboard(Model model, Principal principal) {
		return new ModelAndView("normal/user_dashboard.html");
	}

	// open add contact form

	@GetMapping("/add-contact")
	public ModelAndView openAddContactForm(Model model) {
		model.addAttribute("title", "add-contact");
		model.addAttribute("contact", new Contact());

		return new ModelAndView("normal/add_contact_form.html");
	}

	@PostMapping("/process-contact")
	public ModelAndView processContact(@ModelAttribute Contact contact, Principal principal, HttpSession session) {
//		try {
//		String name=principal.getName();
//		User user =this.userRepository.getUserByUserName(name);
//		
//		// processing and uploading file
//		if (file.isEmpty()){
//			//if the file is empty then try our message
//			System.out.println("file is empty");
//		}
//		else {
		// file to the folder and update the name to contact

//			contact.setImage(file.getOriginalFilename());
//			File savefile = new ClassPathResource("static/img").getFile();
//			
//			Path path = Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
//			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//			
//			System.out.println("image is uploaded");
//			
//			contact.setImage(file.getOriginalFilename());
//			File saveFile = new ClassPathResource("static/img").getFile();
//			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
//			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//			System.out.println("Image uploded successfully");
//			
//		}
//		
//		contact.setUser(user);
//		user.getContact().add(contact);
//		this.userRepository.save(user);
//		System.out.println("Data ="+contact);
//		System.out.println("added to database");
//		
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error "+e.getMessage());
//			e.printStackTrace();
//		}
		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);

//			if (3 > 2) {
//				throw new Exception();
//			}

			contact.setUser(user);
			user.getContact().add(contact);
			this.userRepository.save(user);
			System.out.println("Data =" + contact);
			System.out.println("added to database");

			// message success...
			session.setAttribute("message", new Message("your contact is added !! Add more...", "success"));

		} catch (Exception e) {
			System.out.println("Error " + e);
			e.printStackTrace();

			// message error...
			session.setAttribute("message", new Message("something went wron !! try again...", "danger"));

		}
		return new ModelAndView("normal/add_contact_form.html");
	}

	// show contacts
	@GetMapping("/show-contacts/{page}")
	public ModelAndView showContacts(@PathVariable("page") Integer page, Model m, Principal principal) {
		m.addAttribute("title", "Show User Contacts");

		String username = principal.getName();
		User user = this.userRepository.getUserByUserName(username);

		Pageable pageable = PageRequest.of(page, 5);

		Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(), pageable);
		m.addAttribute("contacts", contacts);
		m.addAttribute("currentpage", page);
		m.addAttribute("totalpages", contacts.getTotalPages());

		return new ModelAndView("normal/show_contacts.html");
	}

	// show perticular contact details

	@RequestMapping("/{cid}/contact")
	public ModelAndView showContactsDetails(@PathVariable("cid") Integer cid, Model model, Principal principal) {

		Optional<Contact> contactOptional = this.contactRepository.findById(cid);
		Contact contact = contactOptional.get();

		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);

		if (user.getId() == contact.getUser().getId()) {
			model.addAttribute("contact", contact);
			model.addAttribute("title", contact.getName());
		}

		return new ModelAndView("normal/contact_details.html");
	}

	// delete contact handler

	@GetMapping("/delete/{cId}")
	public ModelAndView deleteContact(@PathVariable("cId") Integer cid, Model model, HttpSession session,
			Principal principal) {

//		System.out.println("CID " + cid);
//
//		Contact contact = this.contactRepository.findById(cid).get();
//
//		User user = this.userRepository.getUserByUserName(principal.getName());
//		user.getContact().remove(contact);
//		
//		// check assignment....
//		
////  	this.userRepository.save(user);
//
//		System.out.println("DELETED");
//
//		session.setAttribute("message", new Message("Contact deleted successfully...!!", "success"));
		Contact contact=this.contactRepository.findById(cid).get();
//		Contact contact=optional.get();
		System.out.println(contact.getCid());
		contact.setUser(null);
//		this.contactRepository.delete(contact);
		
		User user2 = this.userRepository.getUserByUserName(principal.getName());
		user2.getContact().remove(contact);
		this.userRepository.save(user2);
		System.out.println("DELETED");
		session.setAttribute("message", new Message("contact deleted successfully...","success"));

		return new ModelAndView("redirect:/user/show-contacts/0");
	}
	
	//open update form handler
	
	@PostMapping("/update-contact/{cId}")
	public ModelAndView updateForm(@PathVariable("cId") Integer cid, Model m)
	{
		m.addAttribute("title","Update Contact");
		
		Contact contact= this.contactRepository.findById(cid).get();
		m.addAttribute("contact",contact);
		
		return new ModelAndView("normal/update_form.html");
		
	}
	
	// update contact handler
	
	@RequestMapping(value="/process-update",method=RequestMethod.POST)
	public ModelAndView updateHandler(@ModelAttribute Contact contact,Model m,HttpSession session,
			Principal principal)
	{
		try {
			
			//OLD contact detail
			Contact oldcontactDetails = this.contactRepository.findById(contact.getCid()).get();
			//image
//			if(!file.isEmpty())
//			{
//				//file work
//				//rewriter
//				
//				//update new photo
//				File saveFile = new ClassPathResource("static/img").getFile();
//				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
//				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//				contact.setImage(file.getOriginalFilename());
//				
//			}
//			else {
//				
//				contact.setImage(oldcontactDetail.getImage());
//			}
			
			User user=this.userRepository.getUserByUserName(principal.getName());
			contact.setUser(user);
			
			  this.contactRepository.save(contact);
			session.setAttribute("message",new Message("your contact is updated....","success!!"));
			
			
	} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println("contact name "+contact.getName());
		System.out.println("contact Id "+contact.getCid());

		return new ModelAndView("redirect:/user"+contact.getCid()+"/contact");
		}
	
	// your profile handler
		@GetMapping("/profile")
		public ModelAndView yourProfile(Model model) {

			model.addAttribute("title", "Profile Page");

			return new ModelAndView("/normal/profile.html");
		}
		
		//open settings handler
		@GetMapping("/settings")
		public ModelAndView openSettings()
		{
			
			return new ModelAndView("normal/settings.html");
		}
		
		//change password handler
		@PostMapping("/change-password")
		public ModelAndView changePassword(@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword,Principal principal,HttpSession session)
		{
			
			System.out.println("oldpassword :"+oldpassword);
			System.out.println("newpassword :"+newpassword);
			
			String name= principal.getName();
			User currentUser=this.userRepository.getUserByUserName(name);
			System.out.println(currentUser.getPassword());
			
			if (this.bCryptPasswordEncoder.matches(oldpassword, currentUser.getPassword())) {
				
				//change password
				currentUser.setPassword(this.bCryptPasswordEncoder.encode(newpassword));
				this.userRepository.save(currentUser);
				session.setAttribute("message",new Message("your password is successfully changed....","alert-success!!"));

				
			}else {
				//error......
				session.setAttribute("message",new Message("please enter correct old password....","alert-error!!"));
				return new ModelAndView("redirect:/user/settings");
			}
			

			return new ModelAndView("redirect:/user/index");
		}
}
