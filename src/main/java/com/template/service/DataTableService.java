package com.template.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.formDataDTO.FormDataRequest;
import com.template.formDataDTO.UpdateDataTableDTO;
import com.template.model.DataTable;
import com.template.model.FormTemplate;
import com.template.repository.DataTableRepository;
import com.template.repository.FormFieldDataRepository;
import com.template.repository.FormTemplateRepository;
import com.template.validationConstant.ValidationConstant;

import jakarta.transaction.Transactional;

@Service
public class DataTableService implements ValidationConstant {
	
	@Autowired
	DataTableRepository dataTableRepository;
	
	@Autowired
	FormTemplateRepository formTemplateRepository;
	
	@Autowired
	FormFieldDataRepository formFieldDataRepository;
	
	
	
	//------------------------ Save user information ( POST )-------------------------//
	
	@Transactional
	public ResponseEntity<?> saveFormData(FormDataRequest formDataRequest, MultipartFile image, MultipartFile pdfFiles) {
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

	        // Handle image and pdfFiles
	        if (image != null && !image.isEmpty()) {
	            dataTable.setImage(image.getBytes());
	        }

	        if (pdfFiles != null && !pdfFiles.isEmpty()) {
	            dataTable.setPdfFiles(pdfFiles.getBytes());
	        }

	        dataTableRepository.save(dataTable);

	        return new ResponseEntity<>("Data saved successfully!", HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
	    }
	}


	
	
	
	
//	@Transactional
//	public ResponseEntity<?> saveFormData(FormDataRequest formDataRequest) {
//		
//	    Long formTemplateId = formDataRequest.getFormTemplateId();
//	    JsonNode formData = formDataRequest.getFormData();
//
//	    try {
//	        // Retrieve the FormTemplate
//	        FormTemplate formTemplate = formTemplateRepository.findById(formTemplateId)
//	                .orElseThrow(() -> new RuntimeException("FormTemplate not found"));
//
//	        // Extract expected column names from the formTemplate fields
//	        JsonNode fields = formTemplate.getFields();
//	        
//	        Set<String> expectedColumnNames = new HashSet<>();
//	        
//	        if (fields.isArray()) {
//	            for (JsonNode field : fields) {
//	                String columnName = field.get("columnName").asText();
//	                expectedColumnNames.add(columnName.toLowerCase());
//	            }
//	        }
//
//	        // Validate the formData keys
//	        Iterator<String> fieldNames = formData.fieldNames();
//	        
//	        while (fieldNames.hasNext()) {
//	        	
//	            String fieldName = fieldNames.next().toLowerCase();
//	            
//	            if (!expectedColumnNames.contains(fieldName)) {
//	                return new ResponseEntity<>("Invalid field: " + fieldName, HttpStatus.BAD_REQUEST);
//	            }
//	        }
//
//	        // All keys are valid, proceed to save data
//	        DataTable dataTable = new DataTable();
//	        
//	        dataTable.setFormTemplate(formTemplate);
//	        dataTable.setFieldsData(formData);
//	        
//	        dataTableRepository.save(dataTable);
//
//	        return new ResponseEntity<>("Data saved successfully!", HttpStatus.OK);
//	    } 
//	    catch (Exception e) {
//	        e.printStackTrace();
//	        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
//	    }
//	}
	
	//------------------------------------------------------------------------------------------//
	


	
	
	
	//--------------------- get template data with the data of datatable ( GET )------------------------------//
	
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
	
	//-----------------------------------------------------------------------//
	
	
	
	
	
	//------------------------ get particular user-info by uid ------------------------------//
	
	public ResponseEntity<?> getUserByUID(Long uid){
		
		Optional<DataTable> checkUser = dataTableRepository.findById(uid);
		
		try {
			
			if(checkUser.isPresent()) {
				
				JsonNode userdata = checkUser.get().getFields_Data();
				
				return new ResponseEntity<> (userdata,HttpStatus.OK);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return new ResponseEntity<> ("Opps..! user uid not found", HttpStatus.BAD_REQUEST);
	}
	
	
	//------------------------------------------------------------------------------------------//
	
	
	
	
    //-------------------------Update user entry (PUT/UPDATE)------------------------------------//
	
	@Transactional	
	public ResponseEntity<?> UpdateUserInfo(UpdateDataTableDTO updateDataTableDTO) {
		
	    if (updateDataTableDTO.getUid() == null) {
	        return new ResponseEntity<>("UID must not be null", HttpStatus.BAD_REQUEST);
	    }

	    Optional<DataTable> optionalDataTable = dataTableRepository.findById(updateDataTableDTO.getUid());

	    try {
	    	
	        if (optionalDataTable.isPresent()) {
	        	
	            DataTable dataTable = optionalDataTable.get();
    
	            JsonNode fieldsData = updateDataTableDTO.getFieldsData();

	            for (Iterator<Map.Entry<String, JsonNode>> it = fieldsData.fields(); it.hasNext(); ) {
	            	
	                Map.Entry<String, JsonNode> entry = it.next();
	                
	                String key = entry.getKey().replace(" ", "").toLowerCase();
	                String value = entry.getValue().asText();

	                // Validate each value using regex patterns from the ValidationConstant interface.
	                
	                if (key.equals("fullname") && !value.matches(NAME_isVALID)) {
	                    return new ResponseEntity<>("Invalid full name format", HttpStatus.BAD_REQUEST);
	                }
	                
	                else if (key.equals("address") && !value.matches(ADDRESS_isVALID)) {
	                    return new ResponseEntity<>("Invalid address format", HttpStatus.BAD_REQUEST);
	                } 
	                
	                else if (key.equals("mobilenumber") && !value.matches(MOBILE_NUMBER_PATTERN)) {
	                    return new ResponseEntity<>("Invalid mobile number format", HttpStatus.BAD_REQUEST);
	                }
	                
	                else if (key.equals("email") && !value.matches(EMAIL_PATTERN)) {
	                    return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
	                }
	                // Add more validations as needed
	            }

	            // If all validations pass, update and save the data
	            dataTable.setFieldsData(updateDataTableDTO.getFieldsData());
	            dataTableRepository.save(dataTable);
	            
	            return new ResponseEntity<>("User info updated successfully!", HttpStatus.OK);
	        } 
	        else {
	            throw new RuntimeException("DataTable with UID " + updateDataTableDTO.getUid() + " not found");
	        }
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("An error occurred while updating the DataTable", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
	
	//------------------------------------------------------------------------------------------//
	
	
	
	
	
    //-------------------------delete added user by uid ( DELETE )------------------------------------//
	@Transactional
	public ResponseEntity<?> deleteUserByDataTableId(Long UID){
		
		Optional<DataTable> checkIdOptional = dataTableRepository.findById(UID);
		
		if(checkIdOptional.isPresent()) {
		
			try {
				
				dataTableRepository.deleteById(UID);
				return new ResponseEntity<> ("User with 'uid : "+UID+"' Deleted Successfully!!", HttpStatus.OK);
				
			}
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<> ("User ID not found!!", HttpStatus.NOT_FOUND);
			}
		}
		else{
			return new ResponseEntity<>("Error occurred while deleting user!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
  //------------------------------------------------------------------------------------------//



}
