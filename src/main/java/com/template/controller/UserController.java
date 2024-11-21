package com.template.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.template.model.User;
import com.template.service.UserService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("/save-user-info")
	public ResponseEntity<String> saveUser(@RequestPart("user") User user, @RequestPart("logo") MultipartFile logo ){
		log.info("user saved succesfully!!!");
		return userService.saveUserInfo(user,logo);
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestParam("username") String userName, @RequestParam("password") String password){
		log.info("user login succesfully!!!");
		return userService.login(userName, password);
	}
	
	@GetMapping("/get-user-data")
	public ResponseEntity<Object> getUserData(@RequestParam("username") String username){
		return userService.getUserData(username);
	}
	
	@GetMapping("/get-all-user")
	public List<User> getALlUsers(){
		return userService.getAllUsers();
	}

}
