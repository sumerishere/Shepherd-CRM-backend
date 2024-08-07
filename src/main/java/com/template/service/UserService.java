package com.template.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.BcryptPasswordEncoder.BcryptEncoderConfig;
import com.template.model.User;
import com.template.repository.CommentRepository;
import com.template.repository.UserRepository;
import com.template.validationConstant.ValidationConstant;

import jakarta.transaction.Transactional;

@Service
public class UserService implements ValidationConstant{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	

	@Autowired
	private final BcryptEncoderConfig passwordEncoder;     //imported password encoder to store real password as hashed value in DB.
	
	public UserService(BcryptEncoderConfig passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Transactional
	public ResponseEntity<String> saveUserInfo(User user) {
		  
        try {
        	
        	if(!user.getFullName().matches(NAME_isVALID) || user.getFullName() == null || user.getFullName().isEmpty()) {
        		return new ResponseEntity<>("Valid Name is required!!", HttpStatus.BAD_REQUEST);
        	}
        	
        	if(!user.getAddress().matches(ADDRESS_isVALID) || user.getAddress() == null || user.getAddress().isEmpty()) {
        		return new ResponseEntity<>("Valid address name is required!!", HttpStatus.BAD_REQUEST);
        	}
        	
            // Validate required fields
            if (user.getUserName() == null || user.getUserName().isEmpty()) {
                return new ResponseEntity<>("Username is required", HttpStatus.BAD_REQUEST);
            }
            
	           // Check if username already exists
	           if (userRepository.findByUserName(user.getUserName()).isPresent()) {
	        	   
	               return new ResponseEntity<>("Username is already present", HttpStatus.BAD_REQUEST);
	           }
            
	            
            if (!user.getMobileNumber().matches(MOBILE_NUMBER_PATTERN) || user.getMobileNumber().isEmpty()) {
                return new ResponseEntity<>("Valid Mobile number is required!!", HttpStatus.BAD_REQUEST);
            }
            
            if (!user.getEmail().matches(EMAIL_PATTERN) || user.getEmail().isEmpty()) {
                return new ResponseEntity<>("Valid Email is required!!", HttpStatus.BAD_REQUEST);
            }
            
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                return new ResponseEntity<>("Password is required!!", HttpStatus.BAD_REQUEST);
            }
            
            if (user.getAddress() == null || user.getAddress().isEmpty()) {
                return new ResponseEntity<>("Address is required!!", HttpStatus.BAD_REQUEST);
            }

            // Encode the password
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // Save the user
            userRepository.save(user);
            
	        return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
	            
	        } 
	        catch (Exception e) {
	        	
	            // Handle the exception as needed
	            return new ResponseEntity<>("Error saving user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	
	
	
	public ResponseEntity<?> Login(String userName, String password){
		
		Optional<User>  user = userRepository.findByUserName(userName);
		
		try {
			User userObject = user.get();
			
			if(user.isPresent()) {
				
				if(passwordEncoder.matches(password, userObject.getPassword())) {
					return new ResponseEntity<>(userObject, HttpStatus.OK);
				}
				else {
					return new ResponseEntity<>("password incorrect!!" ,HttpStatus.NOT_FOUND );
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("incorrect!! username" ,HttpStatus.NOT_FOUND );
	}
	  

	
	
}
