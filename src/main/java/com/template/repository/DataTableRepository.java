package com.template.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.template.model.DataTable;

@Repository
public interface DataTableRepository extends JpaRepository<DataTable, Long> {
	
    @Query(value = "SELECT * FROM data_table WHERE template_id = :templateId", nativeQuery = true)
    Optional<List<DataTable>> findByTemplateId(@Param("templateId") Long templateId);
}
