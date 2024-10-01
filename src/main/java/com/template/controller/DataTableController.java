package com.template.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.formDataDTO.FormDataRequest;
import com.template.formDataDTO.UpdateDataTableDTO;
import com.template.model.DataTable;
import com.template.repository.DataTableRepository;
import com.template.service.DataTableService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class DataTableController {
	
	@Autowired
	DataTableService dataTableService;
	
	@Autowired
	DataTableRepository dataTableRepository;
	 
	
//	@PostMapping("/submit-form-data")
//    public ResponseEntity<?> submitFormData(@RequestBody FormDataRequest formDataRequest) {
//        return dataTableService.saveFormData(formDataRequest);
//    }
	
	
//	@PostMapping(value = "/submit-form-data", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//	public ResponseEntity<?> submitFormData(
//	        @RequestPart("formDataRequest") FormDataRequest formDataRequest,
//	        @RequestPart(value = "image", required = false) MultipartFile image,
//	        @RequestPart(value = "pdfFiles", required = false) MultipartFile pdfFiles) {
//
//	    return dataTableService.saveFormData(formDataRequest, image, pdfFiles);
//	}
	
	 @PostMapping(value = "/submit-form-data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<?> submitFormData(
	            @RequestPart("formDataRequest") String formDataRequestJson,
	            @RequestParam(required = false) Map<String, MultipartFile> files) {

	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            FormDataRequest formDataRequest = objectMapper.readValue(formDataRequestJson, FormDataRequest.class);

	            // Process files if any
	            if (files != null && !files.isEmpty()) {
	                for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
	                    MultipartFile file = entry.getValue();
	                    String fileName = file.getOriginalFilename();
	                    if (fileName != null) {
	                        if (fileName.toLowerCase().endsWith(".pdf")) {
	                            formDataRequest.setPdfFiles(file.getBytes());
	                        } else if (fileName.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif)$")) {
	                            formDataRequest.setImage(file.getBytes());
	                        }
	                        // Add more conditions here if you need to handle other file types
	                    }
	                }
	            }

	            log.info("Received form data: {}", formDataRequest);
	            return dataTableService.saveFormData(formDataRequest);

	        } catch (Exception e) {
	            log.error("Error processing request", e);
	            return ResponseEntity.badRequest().body("Error processing request: " + e.getMessage());
	        }
	    }
	
	
	
	@GetMapping("/get-template-data/{templateId}")
	public ResponseEntity<?> getTemplateData(@PathVariable("templateId") Long formtemplateId){
		
		 return dataTableService.getTemplateData(formtemplateId);
	}
	
	@GetMapping("/get-user-by-uid/{uid}")
	public ResponseEntity<?> getUserByUID(@PathVariable("uid") Long uid){
		return dataTableService.getUserByUID(uid);
	}
	
	    
	@PutMapping("/update-user")
    public ResponseEntity<?> updateDataTable(@RequestBody UpdateDataTableDTO updateDataTableDTO) {
		return dataTableService.UpdateUserInfo(updateDataTableDTO);
    }
	
	
	@DeleteMapping("/delete-by-uid/{UID}")
	public ResponseEntity<?> deleteUserByDataTableId(@PathVariable("UID") Long UID){
		return dataTableService.deleteUserByDataTableId(UID);
	}
	
	@GetMapping("/search-client")
	public ResponseEntity<?> searchClientName( @RequestParam("name") String name, Long templateId){
		return dataTableService.searchClientName(name, templateId);
	}
	

}
