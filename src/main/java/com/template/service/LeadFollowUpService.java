package com.template.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import java.io.File;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.template.model.Comment;
import com.template.model.LeadFollowUp;
import com.template.repository.CommentRepository;
import com.template.repository.LeadFollowUpRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class LeadFollowUpService {
	
	@Autowired
	LeadFollowUpRepository leadFollowUpRepository;
	
	@Autowired
	CommentRepository commentRepository;

	@Autowired
	JavaMailSender sender;
	
	
	//---------------------------------------- Lead Mail sending API ----------------------------------------------------//
	
	public void leadMail(String name, String to, String courseType) throws MessagingException, IOException {

	    String subject = "Thanks For!!! Contacting Testing Shastra.";

	    // Create MimeMessage and MimeMessageHelper
	    MimeMessage mime = sender.createMimeMessage();
	    MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true); // 'true' indicates multipart message
	    mimeHelper.setTo(to);
	    mimeHelper.setSubject(subject);

	    // HTML content with embedded image using cid for Instagram,LinkedIn and youtube icons.
	    String body = "<html><body>"
	            + "Hi " + name + ","
	            + "<br><br>Thank you for reaching out to Testing Shastra!"
	            + "<br><br>We are thrilled that you've shown interest in our programs."
	            + "<br>Your selected course is: <b>" + courseType + "</b>."
	            + "<br><br>At Testing Shastra, we are committed to providing top-notch training and support throughout your learning journey. "
	            + "Our courses are designed to equip you with the skills needed to excel in the competitive world of technology."
	            + "<br><br>Whether you're looking to start a new career or enhance your existing expertise, our team of dedicated instructors will guide you every step of the way."
	            + "<br>We believe in fostering a learning environment that encourages growth and success."
	            + "<br><br>To learn more about our training programs, please visit our website at: "
	            + "<a href=\"https://www.testingshastra.com/\">https://www.testingshastra.com/</a>."
	            + "<br><br>Follow us on:   "
	            + "<a margin-top:12px; href=\"https://www.instagram.com/testingshastra/\" target=\"_blank\">"
	            + "<img src='cid:instaImage' style='width:18px; height:18px;' />"
	            + "</a> |  "
	            + "<a margin-top:12px; href=\"https://www.linkedin.com/company/testing-shasrta/\" target=\"_blank\">"
	            + "<img src='cid:linkedinImage' style='width:18px; height:16px; ' />"
	            + "</a> |  "
	            + "<a margin-top:12px; href=\"https://www.youtube.com/@testingshastra/\" target=\"_blank\">"
	            + "<img src='cid:youtubeImage' style='width:18px; height:16px;' /> "
	            + "</a>"
	            + "<br><br>We look forward to helping you achieve your goals!"
	            + "<br><br>Contact details:"
	            + "<br>+91-9130502135 | +91-8484831616 | +91-8484831600" 
	            + "<br>Mail: info@testingshastra.com"
	            + "<br><br>Best regards,<br>Testing Shastra ;)"
	            + "<br><br><img src='cid:adminLogo' style='width:110px; height:110px;' />"
	            + "<br><br><small>*** Please note that this is an automatically generated email and cannot receive replies ***</small>"
	            + "</body></html>";
	    

	    // Set the HTML content
	    mimeHelper.setText(body, true); // true indicates HTML content

	    // Attach the course image
	    FileSystemResource adminLogogRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\Admin-img\\logo.png"));
	    mimeHelper.addInline("adminLogo", adminLogogRes); // 'courseImage' is the Content ID (cid)

	    // Attach Instagram image icon
	    FileSystemResource instaRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\social-media-imgs\\insta-png.webp"));
	    mimeHelper.addInline("instaImage", instaRes); // 'instaImage' is the Content ID (cid)
	    
	    // Attach LinkedIn image icon
	    FileSystemResource linkedinRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\social-media-imgs\\linkedln-png.webp"));
	    mimeHelper.addInline("linkedinImage", linkedinRes); // 'linkedinImage' is the Content ID (cid)
	    
	    FileSystemResource youtubeRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\social-media-imgs\\youtube-png.webp"));
	    mimeHelper.addInline("youtubeImage", youtubeRes);
	    
	    
	    // Attach a PDF file
	    //FileSystemResource pdfRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\course_pdf\\FullStack_+_React.pdf"));
	    //mimeHelper.addAttachment("Your Course FullStack_React.pdf", pdfRes); // Set the file name as it appears to the recipient
	    
	    
	    if(courseType.equals("Java fullStack development")) {
		    FileSystemResource pdfRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\course_pdf\\FullStack_+_React.pdf"));
		    mimeHelper.addAttachment("FullStack_plus_React.pdf", pdfRes); // Set the file name as it appears to the recipient
	    }
	    else if(courseType.equals("Automation Testing")) {
	    	 FileSystemResource pdfRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\course_pdf\\Java-Selenium_Syllabus_.pdf"));
			 mimeHelper.addAttachment("Java-Selenium_Syllabus.pdf", pdfRes); 
	    }
	    else if(courseType.equals("UI/UX")) {
	    	 FileSystemResource pdfRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\course_pdf\\Diploma-in-Fullstack-Development_UI_UX.pdf"));
			 mimeHelper.addAttachment("UI/UX_syllabus.pdf", pdfRes); 
	    }
	    else if(courseType.equals("MERN Stack")) {
	    	 FileSystemResource pdfRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\course_pdf\\Advance_JS_&_React syllabus.pdf"));
			 mimeHelper.addAttachment("MERN_Stack_syllabus.pdf", pdfRes); 
	    }else {
	    	 FileSystemResource pdfRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\course_pdf\\Rest_Api_Testing_syllabus.pdf"));
			 mimeHelper.addAttachment("REST_Api_syllabus.pdf", pdfRes); 
	    }

	    // Send the email
	    sender.send(mime);
	}

	
	//--------------------------------------------------------------------------------------------//
	
	
	
	
	
	//------------------------ save lead information ( POST API ) --------------------------//
	
	public ResponseEntity<?> saveLead(LeadFollowUp leadInfo) {
	    Optional<LeadFollowUp> leadExist = leadFollowUpRepository.findByEmail(leadInfo.getEmail());
	    
	    try {
	        if (!leadExist.isPresent()) {
	        	
	        	 // Get the current date and time
	            LocalDateTime now = LocalDateTime.now();
	            
	            //This truncates the LocalDateTime to discard the seconds and nanoseconds, 
	            //so only the year, month, day, hour, and minute parts are retained.
	            LocalDateTime truncatedTime = now.truncatedTo(ChronoUnit.MINUTES);
	            
	            leadInfo.setCreatedAt(truncatedTime);
	  
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
	        leadFollowUp.setSource(updatedLeadFollowUp.getSource());
	        leadFollowUp.setCourseType(updatedLeadFollowUp.getCourseType());
	        leadFollowUp.setReferName(updatedLeadFollowUp.getReferName());
	        leadFollowUp.setQualification(updatedLeadFollowUp.getQualification());
	        leadFollowUp.setCategory(updatedLeadFollowUp.getCategory());
	        leadFollowUp.setFollowUpDate(updatedLeadFollowUp.getFollowUpDate());

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
	                    } 
	                    else {
	                        return new ResponseEntity<>("Comment length should be under the range (1-151) letters.", HttpStatus.BAD_REQUEST);
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
	    }
	    catch (Exception e) {
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
	
	
	
	
	//---------------------------- search lead by name -----------------------------------------------//
	
	public ResponseEntity<List<?>> searchLeadName(String name) {
		
		return new ResponseEntity<>(leadFollowUpRepository.searchByLeadName(name), HttpStatus.OK);		
	}
	
	//---------------------------------------------------------------------------------------------------//
	
	
	
	 
}
