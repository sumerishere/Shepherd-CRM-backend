package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.UserCommentDTO.CommentRequest;
import com.template.model.Comment;
import com.template.model.User;
import com.template.service.CommentService;
import com.template.service.UserService;

@RestController
@CrossOrigin("*")
public class CommentController {
	
	@Autowired
	UserService userService;

    @Autowired
    private CommentService commentService;
    
    
    
    
    

    @DeleteMapping("/delete-comment/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
    	
        commentService.deleteComment(id);
        
        return ResponseEntity.noContent().build();
    }
    
    
    
    
}