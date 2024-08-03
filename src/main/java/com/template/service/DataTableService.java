package com.template.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.JsonNode;
import com.template.formDataDTO.FormDataRequest;
import com.template.model.DataTable;
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
	public ResponseEntity<?> saveFormData(FormDataRequest formDataRequest) {
		
	    Long formTemplateId = formDataRequest.getFormTemplateId();
	    JsonNode formData = formDataRequest.getFormData();

	    try {
	        // Retrieve the FormTemplate
	        FormTemplate formTemplate = formTemplateRepository.findById(formTemplateId)
	                .orElseThrow(() -> new RuntimeException("FormTemplate not found"));

	        // Extract expected column names from the formTemplate fields
	        JsonNode fields = formTemplate.getFields();
	        
	        Set<String> expectedColumnNames = new HashSet<>();
	        
	        if (fields.isArray()) {
	            for (JsonNode field : fields) {
	                String columnName = field.get("columnName").asText();
	                expectedColumnNames.add(columnName.toLowerCase());
	            }
	        }

	        // Validate the formData keys
	        Iterator<String> fieldNames = formData.fieldNames();
	        
	        while (fieldNames.hasNext()) {
	        	
	            String fieldName = fieldNames.next().toLowerCase();
	            
	            if (!expectedColumnNames.contains(fieldName)) {
	                return new ResponseEntity<>("Invalid field: " + fieldName, HttpStatus.BAD_REQUEST);
	            }
	        }

	        // All keys are valid, proceed to save data
	        DataTable dataTable = new DataTable();
	        
	        dataTable.setFormTemplate(formTemplate);
	        dataTable.setFieldsData(formData);
	        
	        dataTableRepository.save(dataTable);

	        return new ResponseEntity<>("Data saved successfully!", HttpStatus.OK);
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
	    }
	}

	


	
	// get template data with the data of datatable 
	
	public ResponseEntity<?> getTemplateData(Long formtemplateId){
		
		Optional<List<DataTable>> checkTemplateId = dataTableRepository.findByTemplateId(formtemplateId);
		
		try {
			
			if(checkTemplateId.isPresent()) {
				
				List<DataTable> data = checkTemplateId.get();  //object get
				
				return new ResponseEntity<> (data,HttpStatus.OK);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<> ("Opps..! Template id not found", HttpStatus.BAD_REQUEST);
	}
	
	




}
