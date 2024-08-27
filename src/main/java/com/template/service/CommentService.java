package com.template.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.UserCommentDTO.CommentUpdateRequest;
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

    
    
    
    
    //------------------------------- edit created comment (PUT/UPDATE API) --------------------------------//
    
    public ResponseEntity<?> updateComment(Long id, CommentUpdateRequest updateRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (!optionalComment.isPresent()) {
            return new ResponseEntity<>("No comment found with the given ID", HttpStatus.NOT_FOUND);
        }

        Comment comment = optionalComment.get();
        comment.setComment(updateRequest.getComment()); // Update the comment text
        comment.setCreatedAt(LocalDateTime.now()); // Update the createdAt field

        commentRepository.save(comment); // Save the updated comment

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    
    //------------------------------------------------------------------------------------------------------//
    
    
    
    
    
    
    //--------------------------------- get specific comment by its id (GET API) ---------------------------//
    public ResponseEntity<?> getComment(Long id) {
    	
        Optional<Comment> comments = commentRepository.findCommentById(id);
        
        if (!comments.isPresent()) {
            return new ResponseEntity<>("No comments found with the given ID", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }    
    
    //------------------------------------------------------------------------------------------------------//
}