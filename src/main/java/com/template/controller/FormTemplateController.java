package com.template.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.template.model.FormTemplate;
import com.template.model.FormTemplateDTO;
import com.template.service.CommentService;
import com.template.service.FormTemplateService;

@RestController
@CrossOrigin("*")
public class FormTemplateController {
	
	@Autowired
	FormTemplateService formTemplateService;  //user template form autowired.
	
	@Autowired
	CommentService commentService;  //user comment autowired.
	
	
	@PostMapping("/create-template")
    public ResponseEntity<String> saveFormTemplate(@RequestBody FormTemplateDTO formTemplateDTO) {
        return formTemplateService.saveFormTemplate(formTemplateDTO);
    }
	
//	@PostMapping("/create-template")
//	public ResponseEntity<String> createFormTemplate(@RequestBody FormTemplate formTemplate) {
//		
//       formTemplate.setCreatedAt(LocalDateTime.now());  // Set the current date and time
//	   ResponseEntity<String> savedFormTemplate = formTemplateService.saveFormTemplate(formTemplate);
//	    return savedFormTemplate;
//	 }
	
	
	

}
