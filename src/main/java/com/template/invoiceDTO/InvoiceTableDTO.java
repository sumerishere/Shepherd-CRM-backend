package com.template.invoiceDTO;

import lombok.Data;

@Data
public class InvoiceTableDTO {
	
    private Long id;
    private String candidateName;
    private String candidateMobile;
    private String candidateMail;
    private String organizationName;
	private String invoiceCreatedAt;
    
	public InvoiceTableDTO(){}

	public InvoiceTableDTO(Long id, String candidateName, String candidateMobile, String candidateMail,
			String organizationName, String invoiceCreatedAt) {

		this.id = id;
		this.candidateName = candidateName;
		this.candidateMobile = candidateMobile;
		this.candidateMail = candidateMail;
		this.organizationName = organizationName;
		this.invoiceCreatedAt = invoiceCreatedAt;
	}
	
	
}
