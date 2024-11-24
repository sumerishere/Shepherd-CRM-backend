package com.template.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class InvoiceTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String candidateName;
	private String candidateMobile;
	private String candidateMail;
	private String organizationName;
	private String invoiceCreatedAt;
	
	@Column(columnDefinition="LongBlob")
	private byte[] invoicePdf;
}
