package com.template.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.BcryptPasswordEncoder.BcryptEncoderConfig;
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
	
	@Autowired
	private final BcryptEncoderConfig passwordEncoder;     //imported password encoder to store real password as hashed value in DB.
	
	public FormTemplateService(BcryptEncoderConfig passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	
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
	
	
    @Transactional
    public ResponseEntity<?> getAllUserTemplate(String userName, String password) {
    	
        Optional<User> userCheck = userRepository.findByUserName(userName);

        if (userCheck.isPresent() && passwordEncoder.matches(password, userCheck.get().getPassword())) {
        	
            List<FormTemplateDTO> templateDTOs = formTemplateRepository.findAllByUser(userCheck.get()).stream()
                    .map(this::convertToDTO).collect(Collectors.toList());
            
            return new ResponseEntity<>(templateDTOs, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
    }

    private FormTemplateDTO convertToDTO(FormTemplate formTemplate) {
    	
        FormTemplateDTO dto = new FormTemplateDTO();
        dto.setId(formTemplate.getId());
        dto.setFormName(formTemplate.getFormName());
        dto.setCreatedAt(formTemplate.getCreatedAt());
        dto.setFields(formTemplate.getFields());
        dto.setUserName(formTemplate.getUser().getUserName());
        
        return dto;
    }
	
    
    
    //----- get all user info with relationships --------//
    
	//	@Transactional
	//    public ResponseEntity<?> getAllUserTemplate(String userName, String password) {
	//	 
	//        Optional<User> userCheck = userRepository.findByUserName(userName);
	//        
	//        if (userCheck.isPresent() && passwordEncoder.matches(password, userCheck.get().getPassword())) {
	//        	
	//            List<FormTemplate> templates = formTemplateRepository.findAllByUser(userCheck.get());
	//            return new ResponseEntity<>(templates, HttpStatus.OK);
	//        } 
	//        else {
	//            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
	//        }
	//    }
	


}
