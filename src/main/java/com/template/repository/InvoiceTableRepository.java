package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.model.InvoiceTable;

public interface InvoiceTableRepository extends JpaRepository<InvoiceTable, Long>{

}
