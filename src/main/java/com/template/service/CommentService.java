package com.template.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.model.Comment;
//import com.template.model.DeletedComment;
import com.template.repository.CommentRepository;
//import com.template.repository.DeletedCommentRepository;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

//    @Autowired
//    private DeletedCommentRepository deletedCommentRepository;
    
    
    
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
    

    public void deleteComment(Long commentId) {
    	
        Optional<Comment> comment = commentRepository.findById(commentId);
        
        if (comment.isPresent()) {
            // Convert Comment to DeletedComment
            Comment existingComment = comment.get();
            
//            DeletedComment deletedComment = new DeletedComment();
//            
//            deletedComment.setComment(existingComment.getComment());
//            deletedComment.setUser(existingComment.getUser());
//            
//            deletedComment.setDeletedAt(new Timestamp(System.currentTimeMillis()));

            // Save DeletedComment
//            deletedCommentRepository.save(deletedComment);

            // Delete the Comment
            commentRepository.delete(existingComment);
        }
    }
}