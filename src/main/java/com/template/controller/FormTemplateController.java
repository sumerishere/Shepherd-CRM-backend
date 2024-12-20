package com.template.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.template.model.FormTemplateDTO;
import com.template.service.FormTemplateService;

@RestController
@CrossOrigin("*")
public class FormTemplateController {

	private final FormTemplateService formTemplateService;	
	
	
	public FormTemplateController(FormTemplateService formTemplateService) {
		this.formTemplateService = formTemplateService;
	}
	
	
	@PostMapping("/create-template")
    public ResponseEntity<String> saveFormTemplate(@RequestBody FormTemplateDTO formTemplateDTO) {
        return formTemplateService.saveFormTemplate(formTemplateDTO);
    }
	
	@GetMapping("/get-template")
	public ResponseEntity<?> getAllTemplate(@RequestParam("userName") String userName, @RequestParam("password") String password){
		return formTemplateService.getAllUserTemplate(userName, password);
	}
	
	//---- get template by username ----//
	
	@GetMapping("/get-template-username")
	public ResponseEntity<Object> getTemplateByUsername(@RequestParam("userName") String userName){
		return formTemplateService.getTemplateByUsername(userName);
	}
	

		
}
