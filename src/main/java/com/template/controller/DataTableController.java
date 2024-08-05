package com.template.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.template.formDataDTO.FormDataRequest;
import com.template.repository.DataTableRepository;
import com.template.service.DataTableService;

@RestController
@CrossOrigin("*")
public class DataTableController {
	
	@Autowired
	DataTableService dataTableService;
	
	@Autowired
	DataTableRepository dataTableRepository;
	 
	
	
	@PostMapping("/submit-form-data")
    public ResponseEntity<?> submitFormData(@RequestBody FormDataRequest formDataRequest) {
        return dataTableService.saveFormData(formDataRequest);
    }

	
	@GetMapping("/get-template-data/{templateId}")
	public ResponseEntity<?> getTemplateData(@PathVariable("templateId") Long formtemplateId){
		 return dataTableService.getTemplateData(formtemplateId);
	}
	    
	
	@DeleteMapping("/delete-by-uid/{UID}")
	public ResponseEntity<?> deleteUserByDataTableId(@PathVariable("UID") Long UID){
		return dataTableService.deleteUserByDataTableId(UID);
	}
	

}
