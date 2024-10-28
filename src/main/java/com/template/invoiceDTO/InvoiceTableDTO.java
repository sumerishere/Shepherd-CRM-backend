package com.template.invoiceDTO;

import java.time.LocalDateTime;

public class InvoiceTableDTO {
	
    private Long id;
    private String candidateName;
    private String candidateMobile;
    private String candidateMail;
    private String organizationName;
	private String invoiceCreatedAt;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidateMobile() {
		return candidateMobile;
	}
	public void setCandidateMobile(String candidateMobile) {
		this.candidateMobile = candidateMobile;
	}
	public String getCandidateMail() {
		return candidateMail;
	}
	public void setCandidateMail(String candidateMail) {
		this.candidateMail = candidateMail;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getInvoiceCreatedAt() {
		return invoiceCreatedAt;
	}
	public void setInvoiceCreatedAt(String invoiceCreatedAt) {
		this.invoiceCreatedAt = invoiceCreatedAt;
	}

    
}
