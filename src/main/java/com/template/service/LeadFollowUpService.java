package com.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.repository.LeadFollowUpRepository;

@Service
public class LeadFollowUpService {
	
	@Autowired
	LeadFollowUpRepository leadFollowUpRepository;
	
	
	
}
