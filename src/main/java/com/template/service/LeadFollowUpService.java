package com.template.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.model.Comment;
import com.template.model.LeadFollowUp;
import com.template.repository.LeadFollowUpRepository;

@Service
public class LeadFollowUpService {
	
	@Autowired
	LeadFollowUpRepository leadFollowUpRepository;
	
	
	//------------------------ save lead information ( POST API ) --------------------------//
	
	public ResponseEntity<?> saveLead(LeadFollowUp leadInfo) {
		
		Optional<LeadFollowUp> leadExist = leadFollowUpRepository.findByEmail(leadInfo.getEmail());
		
		try {
			
			if(!leadExist.isPresent()) {
				
					leadFollowUpRepository.save(leadInfo);
					return new ResponseEntity<>("Lead info saved!!",HttpStatus.OK);
				
			}
			else {
				return new ResponseEntity<>("Lead alreay Exist!!",HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal Server Error!! : "+e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	} 
	
	//--------------------------------------------------------------------------------------------//
	
	
	
	//----------------------------- get lead info ( GET API ) --------------------------------------//
	
	public ResponseEntity<?> getLead(Long uid) {
		
		Optional<LeadFollowUp> leadCheck = leadFollowUpRepository.findById(uid);
		
		try {
			if(leadCheck.isPresent()) {
				
				LeadFollowUp leadObject = leadCheck.get();
				
				return new ResponseEntity<> (leadObject,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Opps.. Lead ID-"+uid+" Not Found!!", HttpStatus.BAD_REQUEST);
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal Server Error!! : "+e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	
	//--------------------------------------------------------------------------------------------//
	
	
	
	//--------------------------- Update lead info (PUT/UPDATE API) -----------------------------//
	
	public ResponseEntity<?> updateLead(Long uid ){
		
		Optional<LeadFollowUp> leadCheck = leadFollowUpRepository.findById(uid);
		
		try {
			if(leadCheck.isPresent()) {
				
				LeadFollowUp leadUpdate = leadCheck.get();
				
//				Comment comment = new Comment();
				
				
				leadUpdate.setName(leadUpdate.getName());
				leadUpdate.setEmail(leadUpdate.getEmail());
				leadUpdate.setMobileNumber(leadUpdate.getMobileNumber());
				leadUpdate.setAddress(leadUpdate.getAddress());
//				comment.setComments(leadUpdate.getComments());
				leadUpdate.setCreatedAt(LocalDateTime.now());
				
				return new ResponseEntity<> (leadUpdate,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Opps.. Lead ID-"+uid+" Not Found!!", HttpStatus.BAD_REQUEST);
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal Server Error!! : "+e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	//--------------------------------------------------------------------------------------------//
}
