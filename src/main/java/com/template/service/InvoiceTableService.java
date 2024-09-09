package com.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.template.model.InvoiceTable;
import com.template.repository.InvoiceTableRepository;

@Service
public class InvoiceTableService {
	
	@Autowired
	InvoiceTableRepository invoiceTableRepository;
	
	@Autowired
	JavaMailSender sender;
	
	public void saveInvoice(InvoiceTable invoiceTable) {
        invoiceTableRepository.save(invoiceTable);
    }
	

}
