package com.template.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.template.model.LeadFollowUp;

@Repository
public interface LeadFollowUpRepository extends JpaRepository<LeadFollowUp, Long>{
	
	Optional<LeadFollowUp> findByEmail(String email);
	
	@Query("SELECT lead FROM LeadFollowUp lead WHERE lead.name LIKE %:name%")
    List<LeadFollowUp> searchByLeadName(@Param("name") String name);
}
