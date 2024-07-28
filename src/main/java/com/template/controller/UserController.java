package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
			return userService.saveUserInfo(user);
		}
		catch(Exception e) {
			System.out.println("user not save");
		}
		return  ResponseEntity.ok().body("data saved");
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<?> Login(@RequestParam("username") String userName, @RequestParam("password") String password){
		return userService.Login(userName, password);
	}

}
