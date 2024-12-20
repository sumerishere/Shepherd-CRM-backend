package com.template.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.template.BcryptPasswordEncoder.BcryptEncoderConfig;
import com.template.model.User;
import com.template.repository.UserRepository;
import com.template.validationConstant.ValidationConstant;


@Service
public class UserService implements ValidationConstant{
	
	private final UserRepository userRepository;
	private final BcryptEncoderConfig passwordEncoder;   
	
	
	public UserService(UserRepository userRepository, BcryptEncoderConfig passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	
	//--------------------------------------- user registration ( POST ) --------------------------------------------//
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ResponseEntity<String> saveUserInfo(User user, MultipartFile logo) {
	    try {
	        String validationError = validateUser(user);
	        if (validationError != null) {
	            return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
	        }

	        // Check if username already exists
	        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
	            return new ResponseEntity<>("Username is already present, try another username", HttpStatus.BAD_REQUEST);
	        }

	        // Encode the password
	        String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);

	        // Handle logo
	        if (logo != null && !logo.isEmpty()) {
	            user.setLogo(logo.getBytes());
	        }

	        // Save the user
	        userRepository.save(user);

	        return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Error saving user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	//helper method for user validation
	private String validateUser(User user) {
	    if (isNullOrEmpty(user.getFullName()) || !user.getFullName().matches(NAME_isVALID)) {
	        return "Valid Name is required!!";
	    }
	    if (isNullOrEmpty(user.getAddress()) || !user.getAddress().matches(ADDRESS_isVALID)) {
	        return "Valid address name is required!!";
	    }
	    if (isNullOrEmpty(user.getUserName())) {
	        return "Username is required";
	    }
	    if (!Character.isLetter(user.getUserName().charAt(0))) {
	        return "Username must start with an English letter.";
	    }
	    if (isNullOrEmpty(user.getMobileNumber()) || !user.getMobileNumber().matches(MOBILE_NUMBER_PATTERN)) {
	        return "Valid Mobile number is required!!";
	    }
	    if (isNullOrEmpty(user.getEmail()) || !user.getEmail().matches(EMAIL_PATTERN)) {
	        return "Valid Email is required!!";
	    }
	    if (isNullOrEmpty(user.getPassword())) {
	        return "Password is required!!";
	    }
	    return null; // No validation errors
	}

	private boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
	}
	
	
	//------------------------------------------------------------------------------------------//
	
	
	
	//--------------------------- user Login (GET) --------------------------------//
	
	public ResponseEntity<Object> login(String userName, String password){
		
		Optional<User>  user = userRepository.findByUserName(userName);
		
		try {
			User userObject = user.get();
			
			if(user.isPresent()) {
				
				if(passwordEncoder.matches(password, userObject.getPassword())) {
					return new ResponseEntity<>(userObject, HttpStatus.OK);
				}
				else {
					return new ResponseEntity<>("incorrect!! username and password, Please Try again.." ,HttpStatus.BAD_REQUEST);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("incorrect!! username and password, Please Try again.." ,HttpStatus.NOT_FOUND );
	}
	
	//------------------------------------------------------------------------------------------//
	
	
	
	
	//--------------------------- user data (GET API) --------------------------------//
	
	public ResponseEntity<Object>  getUserData(String username){
		
		Optional<User> user = userRepository.findByUserName(username);
		
		try {
			if(user.isPresent()) {
				return new ResponseEntity<>(user,HttpStatus.OK); 
			}
			else {
				return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//------------------------------------------------------------------------------------------//
	
	
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
}
