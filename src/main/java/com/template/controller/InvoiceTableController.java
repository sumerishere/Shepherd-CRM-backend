package com.template.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.template.invoiceDTO.InvoiceTableDTO;
import com.template.model.InvoiceTable;
import com.template.service.InvoiceTableService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class InvoiceTableController {
	
	
	private final InvoiceTableService  invoiceTableService; 
	
	public InvoiceTableController(InvoiceTableService  invoiceTableService) {
		this.invoiceTableService = invoiceTableService;
	}
	
	
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
	        
	        // Validate file type (ensure it's PDF)
	        if (!invoicePdf.getContentType().equalsIgnoreCase("application/pdf")) {
	            return new ResponseEntity<>("Only PDF files are allowed.", HttpStatus.BAD_REQUEST);
	        }

	        // Validate file size (limit to 5MB, for example)
	        if (invoicePdf.getSize() > 5 * 1024 * 1024) {
	            return new ResponseEntity<>("File size exceeds the 5MB limit.", HttpStatus.BAD_REQUEST);
	        }
	        
	        // Log PDF size for debugging
	        log.info("Received PDF size from frontend: " + invoicePdf.getSize());

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
	    
	    catch (IOException e) {
	        log.info("Error processing invoice file", e.getMessage());
	        return new ResponseEntity<>("Failed to process the invoice PDF.", HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	    catch (Exception e) {
	    	log.info(e.getMessage());
	        return new ResponseEntity<>("Failed to save invoice :"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	
	@GetMapping("/get-all-candidate")
    public List<InvoiceTableDTO> getAllCandidates() {
        return invoiceTableService.getAllCandidates();
    }
	
}
