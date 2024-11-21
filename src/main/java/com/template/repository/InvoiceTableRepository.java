package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.template.model.InvoiceTable;

@Repository
public interface InvoiceTableRepository extends JpaRepository<InvoiceTable, Long>{

}
