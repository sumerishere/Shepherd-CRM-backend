package com.template.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.BcryptPasswordEncoder.BcryptEncoderConfig;
import com.template.model.FormTemplate;
import com.template.model.FormTemplateDTO;
import com.template.model.User;
import com.template.repository.FormTemplateRepository;
import com.template.repository.UserRepository;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FormTemplateService {
	
	private final FormTemplateRepository formTemplateRepository;
	private final UserRepository  userRepository;
	private final BcryptEncoderConfig passwordEncoder;     //imported password encoder to store real password as hashed value in DB.
	
	
	public FormTemplateService(FormTemplateRepository formTemplateRepository,
			                      UserRepository  userRepository,BcryptEncoderConfig passwordEncoder) {
		
		this.formTemplateRepository  = formTemplateRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	
	
	@Transactional
    public ResponseEntity<String> saveFormTemplate(FormTemplateDTO formTemplateDTO) {
        // Fetch the user by userName
        Optional<User> optionalUserObject = userRepository.findByUserName(formTemplateDTO.getUserName());

        if (optionalUserObject.isEmpty()) {
            return new ResponseEntity<>("Invalid userName: " + formTemplateDTO.getUserName(), HttpStatus.BAD_REQUEST);
        }
        
        User user = optionalUserObject.get();
        
        // Fetch the password using the native query
        String userPassword = userRepository.findPasswordByUserName(formTemplateDTO.getUserName());

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

        // Check if the user already has a form template
        List<FormTemplate> existingTemplates = formTemplateRepository.findAllByUser(user);
        if (!existingTemplates.isEmpty()) {
            return new ResponseEntity<>("User already has a template form", HttpStatus.CONFLICT);
        }

        // Create FormTemplate entity
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setFormName(formTemplateDTO.getFormName());
        formTemplate.setCreatedAt(formTemplateDTO.getCreatedAt());
        formTemplate.setFields(convertListToJsonNode(formTemplateDTO.getFields())); // Convert List<FieldDTO> to JsonNode
        formTemplate.setUser(user);
        formTemplate.setPassword(userPassword); // Set the user's password

        // Save the form template
        log.info("formtemplate saved succesfully!!!");
        formTemplateRepository.save(formTemplate);

        return new ResponseEntity<>("Form template saved successfully", HttpStatus.CREATED);
    }

    private JsonNode convertListToJsonNode(List<FormTemplateDTO.FieldDTO> fieldDTOList) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.valueToTree(fieldDTOList);
    }
	
    	
	//-----------------------------------------------------------------------------------------//
	    
	private FormTemplateDTO convertToDTO(FormTemplate formTemplate) {
	
		FormTemplateDTO dto = new FormTemplateDTO();
		
		dto.setId(formTemplate.getId());
		dto.setFormName(formTemplate.getFormName());
		dto.setCreatedAt(formTemplate.getCreatedAt());
		dto.setFields(formTemplate.getFields());
		dto.setUserName(formTemplate.getUser().getUserName());
    
    return dto;
}
	
    @Transactional
    public ResponseEntity<?> getAllUserTemplate(String userName, String password) {
    	
        Optional<User> userCheck = userRepository.findByUserName(userName);

        if (userCheck.isPresent() && passwordEncoder.matches(password, userCheck.get().getPassword())) {
        	
            List<FormTemplateDTO> templateDTOs = formTemplateRepository.findAllByUser(userCheck.get()).stream()
                    .map(this::convertToDTO).collect(Collectors.toList());
            
            log.info("get all templateDTOs");
            return new ResponseEntity<>(templateDTOs, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
    }

   
	
    
    
    public ResponseEntity<?> getTemplateByUsername(String userName ){
    	
    	Optional<User> optionalUserObject = userRepository.findByUserName(userName);
    	try {
    		if(optionalUserObject.isPresent() ) {
    			User  user = optionalUserObject.get();
    			
    			List<FormTemplate> templateObject =  formTemplateRepository.findAllByUser(user);
    			
                log.info("get template by username");
    			return new ResponseEntity<>(templateObject, HttpStatus.OK);
    		}
    	}
    	catch( Exception e ){
    		e.printStackTrace();
    	}
    	return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);
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
