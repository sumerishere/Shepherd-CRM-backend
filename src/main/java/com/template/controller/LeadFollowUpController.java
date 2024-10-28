package com.template.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.template.UserCommentDTO.LeadUpdateRequest;
import com.template.model.LeadFollowUp;
import com.template.repository.LeadFollowUpRepository;
import com.template.service.LeadFollowUpService;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
//@RequestMapping("/api/leads")
@Slf4j
public class LeadFollowUpController {
	
	@Autowired
	LeadFollowUpService leadFollowUpService;
	
	@Autowired
	LeadFollowUpRepository leadFollowUpRepository;
	
	
	@PostMapping("/save-lead")
	public ResponseEntity<?> saveLead(@RequestBody LeadFollowUp leadInfo) throws MessagingException, IOException {
		
		Optional<LeadFollowUp> leadExist = leadFollowUpRepository.findByEmail(leadInfo.getEmail());
		
		try {
			if(!leadExist.isEmpty()) {
				leadFollowUpService.saveLead(leadInfo);
				
				log.info("mail sending");
				leadFollowUpService.leadMail(leadInfo.getName(), leadInfo.getEmail(), leadInfo.getCourseType());
				log.info("mail sended successfully!!");
				
				return ResponseEntity.ok("Lead saved successfully.");
			}
			else {
				return new ResponseEntity<>("Lead already exists!!", HttpStatus.BAD_REQUEST);
			}
        } 
		catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@GetMapping("/get-lead-by-id/{uid}")
	public ResponseEntity<?> getLead(@PathVariable("uid") Long uid){
		return leadFollowUpService.getLead(uid);
	}
	
	
	@PutMapping("/update-lead-by-id/{uid}")
	public ResponseEntity<?> updateLead(@PathVariable("uid") Long uid,@RequestBody LeadUpdateRequest request) {
		 System.out.println("LeadUpdateRequest payload: " + request);
		return leadFollowUpService.updateLead(uid, request.getLeadFollowUp(), request.getComments());         
	}
	
	
	@PutMapping("/update-followup/{uid}")
	public  ResponseEntity<?> updateFollowUp(@PathVariable("uid") Long uid, @RequestBody LeadFollowUp leadObject){
		return leadFollowUpService.updateFollowUp(uid, leadObject);
	}
	
	
	@DeleteMapping("delete-lead-by-id/{uid}")
	public ResponseEntity<?> deleteLead(@PathVariable("uid")Long uid){
		return leadFollowUpService.deleteLead(uid);
	}
	
	@GetMapping("/get-all-lead")
	public ResponseEntity<?> getAllLead(){
		return new ResponseEntity<>(leadFollowUpRepository.findAll(), HttpStatus.CREATED);
	}
	
	@GetMapping("/search-lead-name")
	public ResponseEntity<List<?>> searchLeadByName(@RequestParam("name")String name){
		return leadFollowUpService.searchLeadByName(name);
	}
	
	@GetMapping("/search-lead-mobile")
	public ResponseEntity<List<?>> searchLeadByMobile(@RequestParam("mobileNumber")String mobileNumber ){
		return leadFollowUpService.searchLeadByMobile(mobileNumber);
	}
	
	
}