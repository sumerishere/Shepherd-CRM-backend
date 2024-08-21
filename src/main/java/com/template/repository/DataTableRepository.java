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
    
      // mysql curr version not supported this syntax for json data check
    //@Query(value = "SELECT * FROM data_table WHERE fields_data ->> '$.Full Name' LIKE %:name%", nativeQuery = true)
    //List<DataTable> findClientByName(@Param("name") String name);
    
    
    //------ updated syntax supported for json data in mysql curr version.
    @Query(value = "SELECT * FROM data_table WHERE JSON_UNQUOTE(JSON_EXTRACT(fields_data, '$.\"Full Name\"')) LIKE %:name% AND template_id = :templateId", nativeQuery = true)
    List<DataTable> findClientByNameAndTemplateId(@Param("name") String name, @Param("templateId") Long templateId);


}
