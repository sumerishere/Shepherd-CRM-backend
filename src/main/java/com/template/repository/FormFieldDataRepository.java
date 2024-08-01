package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.template.model.FormFieldData;

@Repository
public interface FormFieldDataRepository extends JpaRepository<FormFieldData, Long> {
	
	// Custom query method to find FormField by columnName
    FormFieldData findByColumnName(String columnName);

}
