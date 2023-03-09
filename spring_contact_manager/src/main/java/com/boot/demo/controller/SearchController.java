package com.boot.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.boot.demo.dao.ContactRepository;
import com.boot.demo.dao.UserRepository;
import com.boot.demo.entities.Contact;
import com.boot.demo.entities.User;

@RestController
public class SearchController {

	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ContactRepository contactrepository;
	
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal)
	{
		System.out.println(query);
		User user = this.userrepository.getUserByUserName(principal.getName());
		List<Contact> contacts = this.contactrepository.findByNameContainingAndUser(query,user);
		return ResponseEntity.ok(contacts);
		
	}
}
