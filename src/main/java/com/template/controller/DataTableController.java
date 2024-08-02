package com.template.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.template.formDataDTO.FormDataRequest;
import com.template.model.DataTable;

import com.template.model.FormFieldData;
import com.template.repository.DataTableRepository;
import com.template.service.DataTableService;

@RestController
@CrossOrigin("*")
public class DataTableController {
	
	@Autowired
	DataTableService dataTableService;
	
	@Autowired
	DataTableRepository dataTableRepository;
	
//	 @PostMapping("/submit-form-data")
//	    public ResponseEntity<?> submitFormData(
//	            @RequestParam("formTemplateId") Long formTemplateId,
//	            @RequestParam Map<String, String> fieldsData,
//	            @RequestParam(value = "image", required = false) MultipartFile image,
//	            @RequestParam(value = "pdf", required = false) MultipartFile pdf
//	    ) {
//	        return dataTableService.saveFormData(formTemplateId, fieldsData, image, pdf);
//	    }
	 
	 
	
	
	@PostMapping("/submit-form-data")
    public ResponseEntity<?> submitFormData(@RequestBody FormDataRequest formDataRequest) {
        return dataTableService.saveFormData(formDataRequest);
    }
	    
	

}
