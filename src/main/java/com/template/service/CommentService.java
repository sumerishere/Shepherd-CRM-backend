package com.template.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.model.Comment;
import com.template.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    
    
    
    
    //----------------------------get lead comments with uid ( GET API )--------------------------------//
    public ResponseEntity<?> getComments(Long uid){
    	
    	List<Comment> checkLead = commentRepository.findByUid(uid);
    	
    	try {
    		
			if(!checkLead.isEmpty()) {
				List<Comment>  comments = commentRepository.findByUid(uid);
				return new ResponseEntity<>(comments, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("lead with Id:"+uid+" not found", HttpStatus.NOT_FOUND);
			}
		} 
    	catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("internal server error!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    //------------------------------------------------------------------------------------------------------//

    
    
}