package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.template.service.LeadFollowUpService;

@RestController
@CrossOrigin("*")
public class LeadFollowUpController {
	
	@Autowired
	LeadFollowUpService leadFollowUpService;
	
	
	
}
