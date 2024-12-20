package com.template.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.template.model.Comment;
import com.template.model.LeadFollowUp;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query(value = "select * from comments c where c.lead_uid =:uid", nativeQuery = true )
	List<Comment> findByUid(@Param("uid") Long uid);
	
	List<Comment> findByLeadFollowUp(LeadFollowUp leadFollowUp);
	
	Optional<Comment> findCommentById(Long id); // Use Optional for better null handling

}