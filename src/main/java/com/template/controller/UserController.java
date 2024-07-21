package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.template.model.User;
import com.template.service.CommentService;
import com.template.service.UserService;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentService commentService;
	
	
	@PostMapping("/save-user-info")
	public ResponseEntity<String> saveUser(@RequestBody User user ) {
		
		try {
			userService.saveUserInfo(user.getUserName(),user.getEmail());
		}
		catch(Exception e) {
			System.out.println("user not save");
		}
		
		return  ResponseEntity.ok("data saved");

	}
	
	

}
