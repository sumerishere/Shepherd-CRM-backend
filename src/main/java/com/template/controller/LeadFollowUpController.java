package com.template.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.template.ApiResponseClass.ApiResponse;
import com.template.UserCommentDTO.InvalidCommentException;
import com.template.UserCommentDTO.LeadUpdateRequest;
import com.template.globalException.LeadNotFoundException;
import com.template.model.LeadFollowUp;
import com.template.repository.LeadFollowUpRepository;
import com.template.service.LeadFollowUpService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse> saveLead(@Valid @RequestBody LeadFollowUp leadInfo) {
		
        try {
            boolean isSaved = leadFollowUpService.saveLead(leadInfo);
            
            if (isSaved) {
            	
                log.info("Mail sending");
                leadFollowUpService.leadMail(leadInfo.getName(), leadInfo.getEmail(), leadInfo.getCourseType());
                log.info("Mail sent successfully!!");
                
                return ResponseEntity.status(HttpStatus.CREATED)
                		.body(new ApiResponse("Lead info saved!!", HttpStatus.CREATED));
            } 
            else {
            	
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body(new ApiResponse("Lead already exists!!", HttpStatus.BAD_REQUEST));
            }
        } 
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
        } 
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ApiResponse("Internal Server Error!! : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
	
	
	
	
	@GetMapping("/get-lead-by-id/{uid}")
	public ResponseEntity<?> getLead(@PathVariable("uid") Long uid){
		return leadFollowUpService.getLead(uid);
	}
	
	
//	@PutMapping("/update-lead-by-id/{uid}")
//	public ResponseEntity<?> updateLead(@PathVariable("uid") Long uid,@RequestBody LeadUpdateRequest request) {
//		 System.out.println("LeadUpdateRequest payload: " + request);
//		return leadFollowUpService.updateLead(uid, request.getLeadFollowUp(), request.getComments());         
//	}
	
	@PutMapping("/update-lead-by-id/{uid}")
	public ResponseEntity<ApiResponse> updateLead(@PathVariable Long uid, @RequestBody LeadUpdateRequest request) {
        log.info("Received update request for lead ID: {}", uid);
        
        try {
            
        	leadFollowUpService.updateLead(uid, request.getLeadFollowUp(), request.getComments());
            
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Lead updated successfully",HttpStatus.OK));
                
        } catch (LeadNotFoundException e) {
        	
            log.error("Lead not found: {}", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND));
                    
        } catch (InvalidCommentException e) {
        	
            log.error("Invalid comment: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
                    
        } catch (Exception e) {
            log.error("Error updating lead", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(
                    "An unexpected error occurred while updating the lead", HttpStatus.INTERNAL_SERVER_ERROR));
        }
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