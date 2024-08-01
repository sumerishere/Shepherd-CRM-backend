package com.template.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.template.model.DataTable;
import com.template.model.FormField;
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
	
	
	@PostMapping("/submit-form-data")
	public void submitFormData(@RequestParam Long formTemplateId, @RequestBody JsonNode formData) {
	    dataTableService.saveFormData(formData, formTemplateId);
	}
	    
	

}
