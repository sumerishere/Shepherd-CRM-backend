package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.template.model.LeadFollowUp;
import com.template.service.LeadFollowUpService;

@RestController
@CrossOrigin("*")
public class LeadFollowUpController {
	
	@Autowired
	LeadFollowUpService leadFollowUpService;
	
	
	@PostMapping("/save-lead")
	public ResponseEntity<?> saveLead(@RequestBody LeadFollowUp leadInfo) {
		return leadFollowUpService.saveLead(leadInfo);
	}
	
	
	@GetMapping("/get-lead-by-id/{uid}")
	public ResponseEntity<?> getLead(@PathVariable("uid") Long uid){
		return leadFollowUpService.getLead(uid);
	}
	
	
	@PutMapping("update-lead-by-id/{uid}")
	public ResponseEntity<?> updateLead(@PathVariable("uid") Long uid){
		return null;
	}
}
