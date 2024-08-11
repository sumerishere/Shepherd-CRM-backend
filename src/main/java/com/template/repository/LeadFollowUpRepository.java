package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.template.model.LeadFollowUp;

@Repository
public interface LeadFollowUpRepository extends JpaRepository<LeadFollowUp, Long>{

}
