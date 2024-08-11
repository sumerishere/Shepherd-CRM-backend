package com.template.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.template.model.LeadFollowUp;

@Repository
public interface LeadFollowUpRepository extends JpaRepository<LeadFollowUp, Long>{

	
	Optional<LeadFollowUp> findByEmail(String email);
}
