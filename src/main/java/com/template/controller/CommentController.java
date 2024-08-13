package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.template.service.CommentService;

@RestController
@CrossOrigin("*")
public class CommentController {
	

    @Autowired
    CommentService commentService;
    
    
    @GetMapping("/get-comments-by-id/{uid}")
    public ResponseEntity<?> getComments(@PathVariable("uid") Long uid){
    	return commentService.getComments(uid);
    }
  
    
}