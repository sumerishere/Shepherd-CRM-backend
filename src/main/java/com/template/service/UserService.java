package com.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.model.User;
import com.template.repository.CommentRepository;
import com.template.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	public User findByUsername(String userName) {
	    return userRepository.findByUserName(userName);
	}
	
	
	public User saveUserInfo(String userName , String email) {
		
		User user = new User();
		
		user.setUserName(userName);
		user.setEmail(email);
		
		return userRepository.save(user);
	}

}
