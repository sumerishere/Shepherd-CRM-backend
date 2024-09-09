package com.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.template.model.InvoiceTable;
import com.template.service.InvoiceTableService;

//import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
//@Slf4j
public class InvoiceTableController {
	
	@Autowired
	InvoiceTableService  invoiceTableService; 
	
	
	@PostMapping("/save-invoice")
    public ResponseEntity<String> saveInvoice(
            @RequestParam("candidateName") String candidateName,
            @RequestParam("candidateMobile") String candidateMobile,
            @RequestParam("candidateMail") String candidateMail,
            @RequestParam("organizationName") String organizationName,
            @RequestParam("invoicePdf") MultipartFile invoicePdf) {
        
        try {
            InvoiceTable invoice = new InvoiceTable();
            invoice.setCandidateName(candidateName);
            invoice.setCandidateMobile(candidateMobile);
            invoice.setCandidateMail(candidateMail);
            invoice.setOrganizationName(organizationName);
            invoice.setInvoicePdf(invoicePdf.getBytes());

            invoiceTableService.saveInvoice(invoice);
            
            return new ResponseEntity<>("Invoice saved successfully!", HttpStatus.OK);
        } 
        catch (Exception e) {
            return new ResponseEntity<>("Failed to save invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
}
