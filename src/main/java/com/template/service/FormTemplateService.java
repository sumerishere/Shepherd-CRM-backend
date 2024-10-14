package com.template.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.BcryptPasswordEncoder.BcryptEncoderConfig;
import com.template.model.FormField;
import com.template.model.FormTemplate;
import com.template.model.FormTemplateDTO;
import com.template.model.User;
import com.template.repository.CommentRepository;
import com.template.repository.FormTemplateRepository;
import com.template.repository.UserRepository;
import java.sql.Statement;
import java.util.Iterator;

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
        formTemplateRepository.save(formTemplate);

        // You can add additional logic here if needed

        return new ResponseEntity<>("Form template saved successfully", HttpStatus.CREATED);
    }

    private JsonNode convertListToJsonNode(List<FormTemplateDTO.FieldDTO> fieldDTOList) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.valueToTree(fieldDTOList);
    }
	
    
    
	
	//--------  dynamic table creation Logic (removed approach) ---------//
	
	
//	private void createDynamicTable(FormTemplateDTO formTemplateDTO) {
//		
//	    JsonNode fields = formTemplateDTO.getFields();
//	    
//	    StringBuilder createTableQuery = new StringBuilder();
//	    
//	    // Ensure table name is properly formatted
//	    String tableName = formTemplateDTO.getFormName().replaceAll("\\s+", "_");
//	    
//	    createTableQuery.append("CREATE TABLE `").append(tableName).append("` (");
//	    createTableQuery.append("`id` INT AUTO_INCREMENT PRIMARY KEY, ");
//
//	    Iterator<Map.Entry<String, JsonNode>> fieldsIterator = fields.fields();
//	    
//	    while (fieldsIterator.hasNext()) {
//	    	
//	        Map.Entry<String, JsonNode> field = fieldsIterator.next();
//	        
//	        String fieldName = field.getKey();
//	        String fieldType = mapFieldType(field.getValue().asText());
//	        
//	        createTableQuery.append("`").append(fieldName).append("` ").append(fieldType);
//	        
//	        if (fieldsIterator.hasNext()) {
//	            createTableQuery.append(", ");
//	        }
//	    }
//	        createTableQuery.append(")");
//
//	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/template_created_db", "root", "root");
//	        		
//	            Statement statement = connection.createStatement()) {
//	            statement.executeUpdate(createTableQuery.toString());
//	        } 
//	        catch (Exception e) {
//	            e.printStackTrace();
//	            // Handle exceptions properly in a real-world application
//	        }
//	    }
//
//	    private String mapFieldType(String jsonType) {
//	    	
//	        switch (jsonType) {
//	        
//	            case "Text(String)":
//	                return "VARCHAR(255)";
//	                
//	            case "Number(int)":
//	                return "INT";
//	                
//	            case "Yes/No button(Radio)":
//	                
//	            	return "VARCHAR(3)";
//	            	
//	            case "Yes/No check(checkbox)":
//	                
//	            	return "VARCHAR(3)";	
//	            	
//	            case "Image":
//	            	return "LONGBLOB";
//	            	
//	            case "Pdf File":
//	            	return "LONGBLOB";
//	            	
//	            	//Add more mappings as needed
//	            	
//	            default:
//	            	
//	                throw new IllegalArgumentException("Unsupported field type: " + jsonType);
//	        }
//	    }
	
	
	
	
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
            
            return new ResponseEntity<>(templateDTOs, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
    }

   
	
    
    
    public ResponseEntity<?> getTemplateByUsername(String userName ){
    	
    	Optional<User> OptionalUserObject = userRepository.findByUserName(userName);
    	try {
    		if(OptionalUserObject.isPresent() ) {
    			User  user = OptionalUserObject.get();
    			
    			List<FormTemplate> templateObject =  formTemplateRepository.findAllByUser(user);
    			
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
