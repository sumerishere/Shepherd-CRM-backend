package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.template.model.DataTable;

@Repository
public interface DataTableRepository extends JpaRepository<DataTable, Long> {
	

}
