package com.template.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.model.FormTemplate;
import com.template.model.FormTemplateDTO;
import com.template.model.User;
import com.template.repository.CommentRepository;
import com.template.repository.FormTemplateRepository;
import com.template.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class FormTemplateService {
	
	
	@Autowired
	FormTemplateRepository formTemplateRepository;
	
	@Autowired
	UserRepository  userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	@Transactional
    public ResponseEntity<String> saveFormTemplate(FormTemplateDTO formTemplateDTO) {
        // Fetch the user by userName
        Optional<User> optionalUserName = userRepository.findByUserName(formTemplateDTO.getUserName());
        
        if (optionalUserName.isEmpty()) {
            return new ResponseEntity<>("Invalid userName: " + formTemplateDTO.getUserName(), HttpStatus.BAD_REQUEST);
        }
        
        User user_name = optionalUserName.get();

        // Validate required fields
        if (formTemplateDTO.getFormName() == null || formTemplateDTO.getFormName().isEmpty()) {
            return new ResponseEntity<>("Form name is required", HttpStatus.BAD_REQUEST);
        }
        if (formTemplateDTO.getCreatedAt() == null) {
            return new ResponseEntity<>("Creation date is required", HttpStatus.BAD_REQUEST);
        }
        if (formTemplateDTO.getFields() == null || formTemplateDTO.getFields().isEmpty()) {
            return new ResponseEntity<>("Fields are required", HttpStatus.BAD_REQUEST);
        }

        // Create FormTemplate entity
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setFormName(formTemplateDTO.getFormName());
        formTemplate.setCreatedAt(formTemplateDTO.getCreatedAt());
        formTemplate.setFields(formTemplateDTO.getFields());
        formTemplate.setUser(user_name);

        // Save the form template
        formTemplateRepository.save(formTemplate);
        return new ResponseEntity<>("Form template saved successfully", HttpStatus.CREATED);
    }
	


}
