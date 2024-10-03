package com.template.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.template.BcryptPasswordEncoder.BcryptEncoderConfig;
import com.template.model.User;
import com.template.repository.CommentRepository;
import com.template.repository.UserRepository;
import com.template.validationConstant.ValidationConstant;


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
	
	
	//--------------------------------------- user registration ( POST ) --------------------------------------------//
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ResponseEntity<?> saveUserInfo(User user, MultipartFile logo) {
		  
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
	               return new ResponseEntity<>("Username is already!! present, try another username", HttpStatus.BAD_REQUEST);
	           }
	           
	            String userName = user.getUserName();   //check username validation.
		   		char firstChar = userName.charAt(0);
		   		
		   		// Check if the first character is valid letter.
		   		if (Character.isDigit(firstChar)) {
		   			return new ResponseEntity<>("Username must start with english letter.", HttpStatus.BAD_REQUEST);
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
            
            if (logo != null && !logo.isEmpty()) {
                byte[] logoBytes = logo.getBytes();
                user.setLogo(logoBytes);  // Set the logo byte[] in the user
            }

            // Save the user
            userRepository.save(user);
            
	        return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
	            
	        } 
	        catch (Exception e) {
	        	
	            // Handle the exception as needed
	            return new ResponseEntity<>("Error saving user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	            
	        }
	    }
	//------------------------------------------------------------------------------------------//
	
	
	
	//--------------------------- user Login (GET) --------------------------------//
	
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
	
	//------------------------------------------------------------------------------------------//

	
	
}
