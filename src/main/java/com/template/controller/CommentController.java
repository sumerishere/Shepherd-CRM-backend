package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.template.UserCommentDTO.CommentUpdateRequest;
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
  
    @GetMapping("/get-comment")
    public ResponseEntity<?> getComment(@RequestParam("commentId") Long id){
    	return  commentService.getComment(id);
    }
    
    @PutMapping("/update-comment")
    public ResponseEntity<?> updateComment(@RequestParam("commentId") Long id, @RequestBody CommentUpdateRequest updateRequest) {
        return commentService.updateComment(id, updateRequest);
    }
}