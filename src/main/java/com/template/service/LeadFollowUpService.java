package com.template.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.model.Comment;
import com.template.model.LeadFollowUp;
import com.template.repository.CommentRepository;
import com.template.repository.LeadFollowUpRepository;

@Service
public class LeadFollowUpService {
	
	@Autowired
	LeadFollowUpRepository leadFollowUpRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	
	
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
	
	public ResponseEntity<?> updateLead(Long uid, LeadFollowUp updatedLeadFollowUp, List<String> newComments) {
		
	    try {
	        // Fetch the existing LeadFollowUp entity by UID
	        LeadFollowUp leadFollowUp = leadFollowUpRepository.findById(uid)
	                .orElseThrow(() -> new RuntimeException("Lead not found with UID: " + uid));

	        // Update the lead's information
	        leadFollowUp.setName(updatedLeadFollowUp.getName());
	        leadFollowUp.setEmail(updatedLeadFollowUp.getEmail());
	        leadFollowUp.setMobileNumber(updatedLeadFollowUp.getMobileNumber());
	        leadFollowUp.setAddress(updatedLeadFollowUp.getAddress());

	        // Save the updated lead information
	        leadFollowUp = leadFollowUpRepository.save(leadFollowUp);
	        
	        // Store new comments
	        for (String commentText : newComments) {
	        	
	            Comment comment = new Comment();  // Create a new Comment object for each comment
	            comment.setComment(commentText);
	            comment.setCreatedAt(LocalDateTime.now());
	            comment.setLeadFollowUp(leadFollowUp); // Associate the comment with the lead
	            
	            commentRepository.save(comment); // Save the newly created Comment
	        }

	        // Return a success response with the updated lead
	        return new ResponseEntity<>("Lead updated successfully", HttpStatus.OK);

	    } catch (Exception e) {
	        // Handle exceptions and return an error response
	        return new ResponseEntity<>("Error updating lead: " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	               
	    }
	}
	
	//---------------------------------------------------------------------------------------------------//
	 
	 
	
	
	//----------------------------------- delete lead by uid ( DELETE API ) ------------------------------------//
	
	public ResponseEntity<?> deleteLead(Long uid){
		
		LeadFollowUp leadFollowUp = leadFollowUpRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("Lead not found with UID: " + uid));
		
		try {
			if(leadFollowUp != null) {
				
				leadFollowUpRepository.deleteById(uid);
				
				return new ResponseEntity<>("lead deleted successfully!! of ID:"+uid, HttpStatus.OK); 
			}
			else {
				return new ResponseEntity<>("lead with Id:"+uid+" not found", HttpStatus.NOT_FOUND); 
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("internal server error: "+e, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	
	//---------------------------------------------------------------------------------------------------//
	 
}
