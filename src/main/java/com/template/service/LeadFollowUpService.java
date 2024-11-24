package com.template.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.template.ApiResponseClass.ApiResponse;
import com.template.model.Comment;
import com.template.model.LeadFollowUp;
import com.template.repository.CommentRepository;
import com.template.repository.LeadFollowUpRepository;
import com.template.validationConstant.ValidationConstant;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LeadFollowUpService implements ValidationConstant {
	
	private  final LeadFollowUpRepository leadFollowUpRepository;
	private  final CommentRepository commentRepository;
	private  final JavaMailSender sender;
	
	//constructor injection instead of field.
	public LeadFollowUpService(LeadFollowUpRepository leadFollowUpRepository,CommentRepository commentRepository,JavaMailSender sender) {
		
		this.leadFollowUpRepository = leadFollowUpRepository;
		this.commentRepository = commentRepository;
		this.sender = sender;
		
	}
	
	
	
	//---------------------------------------- Lead Mail sending API ----------------------------------------------------//
	
	public void leadMail(String name, String to, String courseType) throws MessagingException, IOException {

	    String subject = "Thanks For!!! Contacting Testing Shastra.";
	    MimeMessage mime = sender.createMimeMessage();
	    MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true); // multipart message
	    mimeHelper.setTo(to);
	    mimeHelper.setSubject(subject);

	    String body = "<html><body>"
	            + "Hi " + name + ","
	            + "<br><br>Thank you for reaching out to Testing Shastra!"
	            + "<br><br>We are thrilled that you've shown interest in our programs."
	            + "<br>Your selected course is: <b>" + courseType + "</b>."
	            + "<br><br>At Testing Shastra, we are committed to providing top-notch training and support..."
	            + "<br><br>Follow us on:   "
	            + "<a href=\"https://www.instagram.com/testingshastra/\" target=\"_blank\">"
	            + "<img src='cid:instaImage' style='width:18px; height:18px;' />"
	            + "</a> |  "
	            + "<a href=\"https://www.linkedin.com/company/testing-shasrta/\" target=\"_blank\">"
	            + "<img src='cid:linkedinImage' style='width:18px; height:16px;' />"
	            + "</a> |  "
	            + "<a href=\"https://www.youtube.com/@testingshastra/\" target=\"_blank\">"
	            + "<img src='cid:youtubeImage' style='width:18px; height:16px;' />"
	            + "</a>"
	            + "<br><br>We look forward to helping you achieve your goals!"
	            + "<br><br>Contact details:"
	            + "<br>+91-9130502135 | +91-8484831616 | +91-8484831600" 
	            + "<br>Mail: info@testingshastra.com"
	            + "<br><br>Best regards,<br>Testing Shastra ;) "
	            + "<br><br><img src='cid:adminLogo' style='width:110px; height:110px;' />"
	            + "<br><br><small>*** Please note that this is an automatically generated email and cannot receive replies ***</small>"
	            + "</body></html>";
	 // Set the HTML content
	    mimeHelper.setText(body, true); // true indicates HTML content


	    // Set the HTML content
	    // Attach images using relative paths
	    mimeHelper.addInline("adminLogo", new ClassPathResource("static/Admin-img/logo.png"));
	    mimeHelper.addInline("instaImage", new ClassPathResource("static/social-media-imgs/insta-png.webp"));
	    mimeHelper.addInline("linkedinImage", new ClassPathResource("static/social-media-imgs/linkedln-png.webp"));
	    mimeHelper.addInline("youtubeImage", new ClassPathResource("static/social-media-imgs/youtube-png.webp"));

	    // Attach PDF files based on the course type
	    if (courseType.equals("Java fullStack development")) {
	        mimeHelper.addAttachment("FullStack_plus_React.pdf", new ClassPathResource("static/course_pdf/FullStack_+_React.pdf"));
	    } else if (courseType.equals("Automation Testing")) {
	        mimeHelper.addAttachment("Java-Selenium_Syllabus.pdf", new ClassPathResource("static/course_pdf/Java-Selenium_Syllabus_.pdf"));
	    } else if (courseType.equals("UI/UX")) {
	        mimeHelper.addAttachment("UI/UX_syllabus.pdf", new ClassPathResource("static/course_pdf/Diploma-in-Fullstack-Development_UI_UX.pdf"));
	    } else if (courseType.equals("MERN Stack")) {
	        mimeHelper.addAttachment("MERN_Stack_syllabus.pdf", new ClassPathResource("static/course_pdf/Advance_JS_&_React syllabus.pdf"));
	    } else {
	        mimeHelper.addAttachment("REST_Api_syllabus.pdf", new ClassPathResource("static/course_pdf/Rest_Api_Testing_syllabus.pdf"));
	    }

	    // Send the email
	    sender.send(mime);
	}

	
	
	//--------------------------------------------------------------------------------------------//
	
	
	
	
	
	//------------------------ save lead information ( POST API ) --------------------------//
		
	 public boolean saveLead(LeadFollowUp leadInfo) {
			log.debug("Processing lead creation request: {}", leadInfo);
			
	        Optional<LeadFollowUp> leadExist = leadFollowUpRepository.findByEmail(leadInfo.getEmail());
	        
	        if (!leadExist.isPresent()) {
	        	
	            // Set creation time with format of 12hr-am/pm
	            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
	            
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
	            
	            String formattedTime = now.format(formatter);
	            leadInfo.setCreatedAt(formattedTime);

	            // Save lead info
	            leadFollowUpRepository.save(leadInfo);
	            return true; // Indicate success
	        } 
	        else {
	            return false; // Indicate that the lead already exists
	        }
	    }
	
	//--------------------------------------------------------------------------------------------//
	
	 
	 
	 
    //----------------------------------- add bulk lead by xle file (POST API) ----------------------//
	 
	 public List<LeadFollowUp> processExcelFile(MultipartFile file) throws IOException {
		 
        List<LeadFollowUp> leads = new ArrayList<>();
        
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
        	
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            Map<String, Integer> columnMapping = createColumnMapping(headerRow);
            
            // Start from row 1 as row 0 is header
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            	
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;
                
                LeadFollowUp lead = createLeadFromRow(currentRow, columnMapping);
                if (isValidLead(lead)) {
                    leads.add(lead);
                }
            }
        }
        
        return leadFollowUpRepository.saveAll(leads);
    }

    private Map<String, Integer> createColumnMapping(Row headerRow) {
    	
        Map<String, Integer> columnMapping = new HashMap<>();
        
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
        	
            Cell cell = headerRow.getCell(i);
            
            if (cell != null) {
                columnMapping.put(cell.getStringCellValue().toLowerCase().trim(), i);
            }
        }
        return columnMapping;
    }

    private LeadFollowUp createLeadFromRow(Row row, Map<String, Integer> columnMapping) {
        LeadFollowUp lead = new LeadFollowUp();
        
        // Set each field based on the column mapping
        setCellValue(lead::setName, row, columnMapping.get("name"));
        setCellValue(lead::setEmail, row, columnMapping.get("email"));
        setCellValue(lead::setMobileNumber, row, columnMapping.get("mobile_number"));
        setCellValue(lead::setAddress, row, columnMapping.get("address"));
        setCellValue(lead::setCourseType, row, columnMapping.get("course_type"));
        setCellValue(lead::setSource, row, columnMapping.get("source"));
        setCellValue(lead::setReferName, row, columnMapping.get("refer_name"));
        setCellValue(lead::setQualification, row, columnMapping.get("qualification"));
        setCellValue(lead::setCategory, row, columnMapping.get("category"));
        setCellValue(lead::setFollowUpDate, row, columnMapping.get("follow_up_date"));
        setCellValue(lead::setAssignTo, row, columnMapping.get("assign_to"));
        setCellValue(lead::setStatusType, row, columnMapping.get("status_type"));
        
        // Set creation timestamp
        lead.setCreatedAt(LocalDateTime.now().toString());
        
        return lead;
    }

    private void setCellValue(Consumer<String> setter, Row row, Integer columnIndex) {
    	
        if (columnIndex != null) {
        	
            Cell cell = row.getCell(columnIndex);
            
            if (cell != null) {
                setter.accept(getCellValueAsString(cell));
            }
        }
    }

    private String getCellValueAsString(Cell cell) {
    	
        switch (cell.getCellType()) {
        
            case STRING:
                return cell.getStringCellValue();
                
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());
                
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
                
            default:
                return "";
        }
    }

    private boolean isValidLead(LeadFollowUp lead) {
        // Add validation logic based on @NotNull and other constraints
        return lead.getName() != null && 
               lead.getName().length() >= 2 && 
               lead.getName().length() <= 100 &&
               lead.getEmail() != null &&
               lead.getMobileNumber() != null;
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
				return new ResponseEntity<>
					       ( new ApiResponse("Opps.. Lead ID-"+uid+" Not Found!!", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
			}
			
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>
						  (new ApiResponse("Internal Server Error!! : "+e, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	
	//--------------------------------------------------------------------------------------------//
	
	
	
	
	
	
	//--------------------------- Update lead info (PUT/UPDATE API) -----------------------------//
	
	@Transactional
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
	        leadFollowUp.setAssignTo(updatedLeadFollowUp.getAssignTo());
	        leadFollowUp.setStatusType(updatedLeadFollowUp.getStatusType());

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
	
	
	
	
	
	
	
	//---------------------------------------------------------------------------------------------------//
	 
	 
	
	//------------------------------------ update lead followup details (PUT API) ------------------------//
	
	@Transactional
	public  ResponseEntity<?> updateFollowUp(Long uid, LeadFollowUp leadObject){
		
		try {
			 LeadFollowUp leadFollowUp = leadFollowUpRepository.findById(uid)
		                .orElseThrow(() -> new RuntimeException("Lead not found with UID: " + uid));
			 
			 leadFollowUp.setName(leadObject.getName());
			 
			 if(leadObject.getMobileNumber().matches(MOBILE_NUMBER_PATTERN)) {
				 leadFollowUp.setMobileNumber(leadObject.getMobileNumber());
			 }else {
				 return new ResponseEntity<> ("mobile number invalid",HttpStatus.BAD_REQUEST);
			 }
			 
			 if(leadObject.getEmail().matches(EMAIL_PATTERN)) {
				 leadFollowUp.setEmail(leadObject.getEmail());
			 }else {
				 return new ResponseEntity<> ("invalid!! email",HttpStatus.BAD_REQUEST);
			 }
			
			 leadFollowUp.setFollowUpDate(leadObject.getFollowUpDate());
			 leadFollowUp.setAssignTo(leadObject.getAssignTo());
			 leadFollowUp.setStatusType(leadObject.getStatusType());
			 
			 leadFollowUp = leadFollowUpRepository.save(leadFollowUp);
			 
			 return new ResponseEntity<>("Lead updated successfully", HttpStatus.OK);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error updating lead: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	
	
	
	//---------------------------- search lead by name & mobile number (GET API)-----------------------------------------------//
	
	      //-------- search by name ----------//
	
	public ResponseEntity<List<?>> searchLeadByName(String name) {
	    
		if (name != null && !name.isEmpty()) {
	        // Only name provided
	        return new ResponseEntity<>(leadFollowUpRepository.searchByLeadName(name), HttpStatus.OK);
	        
	    } else {
	        // Neither name nor mobile number provided, return empty result
	        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
	    }
	}
	
	
              //------ search by mobile number --------//
	
	public ResponseEntity<List<?>> searchLeadByMobile(String mobileNumber) {
		
		if (mobileNumber != null && !mobileNumber.isEmpty()) {
	        // Only mobile number provided
	        return new ResponseEntity<>(leadFollowUpRepository.searchByMobileNumber(mobileNumber), HttpStatus.OK);
		}
		else {
	        // Neither name nor mobile number provided, return empty result
			log.info("-----> lead not found... <----");
	        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
	    }
	}
	
	//---------------------------------------------------------------------------------------------------//
	
	
	
	 
}
