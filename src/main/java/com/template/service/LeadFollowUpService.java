package com.template.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.template.model.Comment;
import com.template.model.LeadFollowUp;
import com.template.repository.CommentRepository;
import com.template.repository.LeadFollowUpRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
//import jakarta.transaction.Transactional;

@Service
public class LeadFollowUpService {
	
	@Autowired
	LeadFollowUpRepository leadFollowUpRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	@Autowired
	JavaMailSender sender;
	
	
	//---------------------------------------- Lead Mail sending API ----------------------------------------------------//
	
	public void leadMail(String name, String to, String courseType) throws MessagingException,IOException {
		
		 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		 simpleMailMessage.setTo(to);
		 
		 String subject = "Thanks For!!! Contacting Testing Shastra.";
		 
		 simpleMailMessage.setSubject(subject);
		 
		 String body = "Hi " + name + ","
				    + "\n\nThank you for reaching out to Testing Shastra!"
				    + "\n\nWe are thrilled that you've shown interest in our programs. \nYour selected Course Type is: " + courseType + "."
				    + "\n\nAt Testing Shastra, we are committed to providing top-notch training and support throughout your learning journey. Our courses are designed to equip you with the skills needed to excel in the competitive world of technology."
				    + "\n\nWhether you're looking to start a new career or enhance your existing expertise, our team of dedicated instructors will guide you every step of the way. We believe in fostering a learning environment that encourages growth and success."
				    + "\n\nTo learn more about our training programs, please visit our website at: \"https://www.testingshastra.com/\" Testing Shastra."
				    + "\n\nWe look forward to helping you achieve your goals!"
				    + "\n\nBest regards,"
				    + "\nTesting Shastra ;)"
				    + "\n\n\n*** Please note that this is an automatically generated email and cannot receive replies ***";

		 
		 MimeMessage mime = sender.createMimeMessage();
		 MimeMessageHelper mimeHelper = new MimeMessageHelper(mime);
		 mimeHelper.setTo(to);
		 mimeHelper.setSubject(subject);
		 mimeHelper.setText(body);
		 
		 sender.send(mime);
		 
	}
	
	//--------------------------------------------------------------------------------------------//
	
	
	
	
	
	//------------------------ save lead information ( POST API ) --------------------------//
	
	public ResponseEntity<?> saveLead(LeadFollowUp leadInfo) {
	    Optional<LeadFollowUp> leadExist = leadFollowUpRepository.findByEmail(leadInfo.getEmail());
	    
	    try {
	        if (!leadExist.isPresent()) {
	            // Set createdAt to the current datetime
	            leadInfo.setCreatedAt(LocalDateTime.now());
	  
	            leadFollowUpRepository.save(leadInfo);
	            return new ResponseEntity<>("Lead info saved!!", HttpStatus.OK);
	        } 
	        else {
	            return new ResponseEntity<>("Lead already exists!!", HttpStatus.BAD_REQUEST);
	        }
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Internal Server Error!! : " + e, HttpStatus.INTERNAL_SERVER_ERROR);
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
	        leadFollowUp = leadFollowUpRepository.save(leadFollowUp); // Only save once after all changes

	        // Process and save new comments
	        if (newComments != null && !newComments.isEmpty()) {
	            List<Comment> commentObjects = new ArrayList<>();

	            for (String commentText : newComments) {
	                if (commentText != null && !commentText.trim().isEmpty()) { // Check for non-empty comments
	                    if (commentText.length() <= 151) { // Validate comment length
	                        Comment comment = new Comment();
	                        comment.setComment(commentText);
	                        comment.setCreatedAt(LocalDateTime.now());
	                        comment.setLeadFollowUp(leadFollowUp);
	                        commentObjects.add(comment);
	                    } else {
	                        return new ResponseEntity<>("Comment length should be under the range (1-151)", HttpStatus.BAD_REQUEST);
	                    }
	                }
	            }

	            // Save valid comments
	            if (!commentObjects.isEmpty()) {
	                commentRepository.saveAll(commentObjects);
	            }
	        }

	        // Return success response
	        return new ResponseEntity<>("Lead updated successfully", HttpStatus.OK);

	    } catch (Exception e) {
	        // Handle exceptions and return an error response
	        return new ResponseEntity<>("Error updating lead: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



	
	
//	public ResponseEntity<?> updateLead(Long uid, LeadFollowUp updatedLeadFollowUp, List<String> newComments) {
//		
//	    try {
//	        // Fetch the existing LeadFollowUp entity by UID
//	        LeadFollowUp leadFollowUp = leadFollowUpRepository.findById(uid)
//	                .orElseThrow(() -> new RuntimeException("Lead not found with UID: " + uid));
//
//	        // Update the lead's information
//	        leadFollowUp.setName(updatedLeadFollowUp.getName());
//	        leadFollowUp.setEmail(updatedLeadFollowUp.getEmail());
//	        leadFollowUp.setMobileNumber(updatedLeadFollowUp.getMobileNumber());
//	        leadFollowUp.setAddress(updatedLeadFollowUp.getAddress());
//
//	        // Save the updated lead information
//	        leadFollowUp = leadFollowUpRepository.save(leadFollowUp);
//	        
//	        // Store new comments
//	        for (String commentText : newComments) {
//	        	
//	        	int commentLength = 151;  //consider comment length limit.
//	            Comment comment = new Comment();  // Create a new Comment object for each comment
//	            
//	            if(commentText.length() < commentLength) {
//	            	comment.setComment(commentText);
//		            comment.setCreatedAt(LocalDateTime.now());
//		            comment.setLeadFollowUp(leadFollowUp); // Associate the comment with the lead
//		            
//		            commentRepository.save(comment); // Save the newly created Comment
//	            }
//	            else {
//	            	return new ResponseEntity<>("Comment length should be under the range(1-151)", HttpStatus.BAD_REQUEST);
//	            }
//	            
//	        }
//
//	        // Return a success response with the updated lead
//	        return new ResponseEntity<>("Lead updated successfully", HttpStatus.OK);
//
//	    } catch (Exception e) {
//	        // Handle exceptions and return an error response
//	        return new ResponseEntity<>("Error updating lead: " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//	               
//	    }
//	}
	
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
