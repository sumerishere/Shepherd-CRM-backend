package com.template.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.formDataDTO.FormDataRequest;
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
	
	
	
//	 @Transactional
//	    public ResponseEntity<?> saveFormData(Long formTemplateId, Map<String, String> fieldsData, MultipartFile image, MultipartFile pdf) {
//	        try {
//	            // Retrieve the FormTemplate
//	            FormTemplate formTemplate = formTemplateRepository.findById(formTemplateId)
//	                    .orElseThrow(() -> new RuntimeException("FormTemplate not found"));
//
//	            // Extract expected column names from the formTemplate fields
//	            JsonNode fields = formTemplate.getFields();
//	            Set<String> expectedColumnNames = new HashSet<>();
//	            if (fields.isArray()) {
//	                for (JsonNode field : fields) {
//	                    String columnName = field.get("columnName").asText();
//	                    expectedColumnNames.add(columnName.toLowerCase());
//
//	                    // Check for image or PDF data type and handle accordingly
//	                    String dataType = field.get("dataType").asText();
//	                    if ("Image".equalsIgnoreCase(dataType) && image != null && !image.isEmpty()) {
//	                        // Handle image upload
//	                        fieldsData.remove(columnName);
//	                    } else if ("Pdf File".equalsIgnoreCase(dataType) && pdf != null && !pdf.isEmpty()) {
//	                        // Handle PDF upload
//	                        fieldsData.remove(columnName);
//	                    }
//	                }
//	            }
//
//	            // Validate the fieldsData keys
//	            for (String fieldName : fieldsData.keySet()) {
//	                if (!expectedColumnNames.contains(fieldName.toLowerCase())) {
//	                    return new ResponseEntity<>("Invalid field: " + fieldName, HttpStatus.BAD_REQUEST);
//	                }
//	            }
//
//	            // Create and populate DataTable entity
//	            DataTable dataTable = new DataTable();
//	            dataTable.setFormTemplate(formTemplate);
//
//	            // Store form data as JSON
//	            ObjectMapper objectMapper = new ObjectMapper();
//	            JsonNode jsonFieldsData = objectMapper.convertValue(fieldsData, JsonNode.class);
//	            dataTable.setFieldsData(jsonFieldsData);
//
//	            // Save image and PDF files as BLOBs
//	            if (image != null && !image.isEmpty()) {
//	                dataTable.setImage(image.getBytes());
//	            }
//
//	            if (pdf != null && !pdf.isEmpty()) {
//	                dataTable.setPdfFiles(pdf.getBytes());
//	            }
//
//	            // Save the DataTable entity
//	            dataTableRepository.save(dataTable);
//
//	            return new ResponseEntity<>("Data saved successfully!", HttpStatus.OK);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
//	        }
//	    }
	
	
	
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
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
	    }
	}




}
