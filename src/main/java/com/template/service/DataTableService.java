package com.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.template.model.DataTable;
import com.template.model.FormFieldData;
import com.template.model.FormTemplate;
import com.template.repository.DataTableRepository;
import com.template.repository.FormFieldDataRepository;
import com.template.repository.FormTemplateRepository;

import jakarta.transaction.Transactional;

@Service
public class DataTableService {
	
	@Autowired
	DataTableRepository dataTableRepository;
	
	@Autowired
	FormTemplateRepository formTemplateRepository;
	
	@Autowired
	FormFieldDataRepository formFieldDataRepository;
	
    
	@Transactional
	public void saveFormData(JsonNode formData, Long formTemplateId) {
		
	    // Retrieve the FormTemplate
	    FormTemplate formTemplate = formTemplateRepository.findById(formTemplateId)
	            .orElseThrow(() -> new RuntimeException("FormTemplate not found"));

	    DataTable dataTable = new DataTable();
	    
	    dataTable.setFormTemplate(formTemplate); // Set the FormTemplate
	    
	    dataTable.setFieldsData(formData); // Set the form data as JSON

	    // Save DataTable entity
	    dataTableRepository.save(dataTable);
	}



}
