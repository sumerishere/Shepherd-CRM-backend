package com.template.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.template.model.FormTemplate;
import com.template.model.User;

@Repository
public interface FormTemplateRepository extends JpaRepository<FormTemplate, Long> {
	
	List<FormTemplate> findAllByUser(User user);


}
