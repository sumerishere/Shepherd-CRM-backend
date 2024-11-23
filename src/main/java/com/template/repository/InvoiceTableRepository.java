package com.template.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.template.invoiceDTO.InvoiceTableDTO;
import com.template.model.InvoiceTable;

@Repository
public interface InvoiceTableRepository extends JpaRepository<InvoiceTable, Long>{
	
	@Query("SELECT user from InvoiceTable user where user.candidateMobile LIKE CONCAT('%',:mobileNumber,'%')")
	List<InvoiceTableDTO> searchInvoiceByMobile(@Param("mobileNumber") String candidateMobile);

}
