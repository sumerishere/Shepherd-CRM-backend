package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.template.model.FormTemplate;

@Repository
public interface FormTemplateRepository extends JpaRepository<FormTemplate, Long> {

}
