package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.template.model.User;
import com.template.service.CommentService;
import com.template.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentService commentService;
	
	
	@PostMapping("/save-user-info")
	public ResponseEntity<?> saveUser(@RequestPart("user") User user, @RequestPart("logo") MultipartFile Logo ){
		return userService.saveUserInfo(user,Logo);
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<?> Login(@RequestParam("username") String userName, @RequestParam("password") String password){
		return userService.Login(userName, password);
	}

}
