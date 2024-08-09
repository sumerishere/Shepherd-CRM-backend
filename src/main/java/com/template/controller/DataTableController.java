package com.template.controller;


import org.springframework.beans.factory.annotation.Autowired;
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

import com.template.formDataDTO.FormDataRequest;
import com.template.formDataDTO.UpdateDataTableDTO;
import com.template.model.DataTable;
import com.template.repository.DataTableRepository;
import com.template.service.DataTableService;

@RestController
@CrossOrigin("*")
public class DataTableController {
	
	@Autowired
	DataTableService dataTableService;
	
	@Autowired
	DataTableRepository dataTableRepository;
	 
	
//	@PostMapping("/submit-form-data")
//    public ResponseEntity<?> submitFormData(@RequestBody FormDataRequest formDataRequest) {
//        return dataTableService.saveFormData(formDataRequest);
//    }
//	
	
	@PostMapping("/submit-form-data")
	public ResponseEntity<?> submitFormData(
	        @RequestPart("formDataRequest") FormDataRequest formDataRequest,
	        @RequestPart(value = "image", required = false) MultipartFile image,
	        @RequestPart(value = "pdfFiles", required = false) MultipartFile pdfFiles) {

	    return dataTableService.saveFormData(formDataRequest, image, pdfFiles);
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
	

}
