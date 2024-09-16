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

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class InvoiceTableController {
	
	@Autowired
	InvoiceTableService  invoiceTableService; 
	
	
	@PostMapping("/save-invoice")
	public ResponseEntity<?> saveInvoice(
	        @RequestParam(value="billedToName",required=true) String candidateName,
	        @RequestParam(value="candidateMobile",required=true) String candidateMobile,
	        @RequestParam(value="candidateMail",required=true) String candidateMail,
	        @RequestParam(value="billedByName",required=true) String organizationName,
	        @RequestParam(value="invoicePdf",required=true) MultipartFile invoicePdf) {

	    try {
	        // Check for non-empty fields
	        if (candidateName == null || candidateName.isBlank() ||
	            candidateMobile == null || candidateMobile.isBlank() ||
	            candidateMail == null || candidateMail.isBlank() ||
	            organizationName == null || organizationName.isBlank() ||
	            invoicePdf.isEmpty()) {
	            return new ResponseEntity<>("All fields are required and must be non-empty.", HttpStatus.BAD_REQUEST);
	        }

	        // If all fields are valid, proceed with saving the invoice
	        InvoiceTable invoice = new InvoiceTable();
	        invoice.setCandidateName(candidateName);
	        invoice.setCandidateMobile(candidateMobile);
	        invoice.setCandidateMail(candidateMail);
	        invoice.setOrganizationName(organizationName);
	        invoice.setInvoicePdf(invoicePdf.getBytes());

	        log.info("Data sending...");
	        invoiceTableService.saveInvoice(invoice);
	        log.info("Data saved successfully!");

	        log.info("Sending mail...");
	        invoiceTableService.sendInvoiceMail(candidateMail, candidateName, invoice.getId());
	        log.info("Mail sent!");

	        return new ResponseEntity<>("Invoice saved successfully!", HttpStatus.OK);
	    } 
	    catch (Exception e) {
	        return new ResponseEntity<>("Failed to save invoice", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	
}
