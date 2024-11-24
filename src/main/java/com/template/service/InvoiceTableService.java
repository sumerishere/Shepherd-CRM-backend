package com.template.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.template.invoiceDTO.InvoiceTableDTO;
import com.template.model.InvoiceTable;
import com.template.repository.InvoiceTableRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class InvoiceTableService {
	
	private final InvoiceTableRepository invoiceTableRepository;
	private final JavaMailSender sender;
	
	
	public InvoiceTableService(InvoiceTableRepository invoiceTableRepository, JavaMailSender sender) {
		this.invoiceTableRepository = invoiceTableRepository;
		this.sender = sender;
	}
	
	
	//----------------------------------- invoice mail api ---------------------------------------//
	
	public void sendInvoiceMail(String to, String candidateName,Long invoiceId) throws MessagingException {
		
		 // Fetch the InvoiceTable entry by ID
        InvoiceTable invoice = invoiceTableRepository.findById(invoiceId)
                                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        
        // Get the PDF as byte[]
        byte[] pdfBytes = invoice.getInvoicePdf();
        
        log.info("PDF size in mail: " + pdfBytes.length);
        // Check if the PDF is not empty
        if (pdfBytes == null || pdfBytes.length == 0) {
            throw new RuntimeException("The PDF data is empty");
        }
		
		
	 	String subject = "Fees Acknowledgement!!!, Testing Shastra.";

	    // Create MimeMessage and MimeMessageHelper
	    MimeMessage mime = sender.createMimeMessage();
	    MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true); // 'true' indicates multipart message
	    mimeHelper.setTo(to);
	    mimeHelper.setSubject(subject);

	    // HTML content with embedded image using cid for Instagram,LinkedIn and youtube icons.
	    String body = "<html><body>"
	            + "Hi " + candidateName + ","
	            + "<br><br>Thank you for joining Testing Shastra!!!"
	            + "<br><br><p><strong style=\"font-size: 14px;\">Find below your fees acknowledgement pdf.</strong></p>"
	            + "<br><br>We are thrilled that you've shown interest in our programs."
	            + "<br><br>At Testing Shastra, we are committed to providing top-notch training and support throughout your learning journey. "
	            + "Our courses are designed to equip you with the skills needed to excel in the competitive world of technology."
	            + "<br><br>Whether you're looking to start a new career or enhance your existing expertise, our team of dedicated instructors will guide you every step of the way."
	            + "<br>We believe in fostering a learning environment that encourages growth and success."
	            + "<br><br>To learn more about our training programs, please visit our website at: "
	            + "<a href=\"https://www.testingshastra.com/\">https://www.testingshastra.com/</a>."
	            + "<br><br>Follow us on:   "
	            + "<a margin-top:12px; href=\"https://www.instagram.com/testingshastra/\" target=\"_blank\">"
	            + "<img src='cid:instaImage' style='width:18px; height:18px;' />"
	            + "</a> |  "
	            + "<a margin-top:12px; href=\"https://www.linkedin.com/company/testing-shasrta/\" target=\"_blank\">"
	            + "<img src='cid:linkedinImage' style='width:18px; height:16px; ' />"
	            + "</a> |  "
	            + "<a margin-top:12px; href=\"https://www.youtube.com/@testingshastra/\" target=\"_blank\">"
	            + "<img src='cid:youtubeImage' style='width:18px; height:16px;' /> "
	            + "</a>"
	            + "<br><br>We look forward to helping you achieve your goals!"
	            + "<br><br>Contact details:"
	            + "<br>+91-9130502135 | +91-8484831616 | +91-8484831600" 
	            + "<br>Mail: info@testingshastra.com"
	            + "<br><br>Best regards,<br>Testing Shastra ;)"
	            + "<br><br><img src='cid:adminLogo' style='width:110px; height:110px;' />"
	            + "<br><br><small>*** Please note that this is an automatically generated email and cannot receive replies ***</small>"
	            + "</body></html>";
	    

		// Set the HTML content
	    mimeHelper.setText(body, true); // true indicates HTML content
	    
	    
	    mimeHelper.addInline("adminLogo", new ClassPathResource("static/Admin-img/logo.png"));
	    mimeHelper.addInline("instaImage", new ClassPathResource("static/social-media-imgs/insta-png.webp"));
	    mimeHelper.addInline("linkedinImage", new ClassPathResource("static/social-media-imgs/linkedln-png.webp"));
	    mimeHelper.addInline("youtubeImage", new ClassPathResource("static/social-media-imgs/youtube-png.webp"));

	    // Convert byte[] to ByteArrayResource for the PDF attachment
        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

        // Attach the PDF with a custom filename
        mimeHelper.addAttachment("Fees_Acknowledgement.pdf", pdfResource);

	    // Send the email
	    sender.send(mime);
	}
	
	//-----------------------------------------------------------------------------------------//
	
	
	
	
	//------------------- save invoice pdf with lead info (POST API) -------------------------//
	
	public ResponseEntity<String> saveInvoice(InvoiceTable invoiceTable) {
		
	    // Simply save the invoice, assuming validation is already done in the controller
		 // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        
        //This truncates the LocalDateTime to discard the seconds and nanoseconds, 
        //so only the year, month, day, hour, and minute parts are retained.
        LocalDateTime truncatedTime = now.truncatedTo(ChronoUnit.MINUTES);
        
     // Define formatter to include AM/PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        // Format truncated time with AM/PM
        String formattedTime = truncatedTime.format(formatter);
        
        invoiceTable.setInvoiceCreatedAt(formattedTime);
        
	    invoiceTableRepository.save(invoiceTable);
	    return new ResponseEntity<>("Invoice data saved", HttpStatus.CREATED);
	}

	
	//-----------------------------------------------------------------------------------------//
	
	
	
	
	
	//------------------- Get sended invoice candidates details (GET API)  -------------------------//
	
	public List<InvoiceTableDTO> getAllCandidates() {
		
	    return invoiceTableRepository.findAll().stream().map(invoice -> {
	    	
	        InvoiceTableDTO dto = new InvoiceTableDTO();
	        
	        dto.setId(invoice.getId());
	        dto.setCandidateName(invoice.getCandidateName());
	        dto.setCandidateMobile(invoice.getCandidateMobile());
	        dto.setCandidateMail(invoice.getCandidateMail());
	        dto.setOrganizationName(invoice.getOrganizationName());
	        dto.setInvoiceCreatedAt(invoice.getInvoiceCreatedAt());
	        
	        return dto;
	        
	    }).collect(Collectors.toList());
	}
	
	//-----------------------------------------------------------------------------------------//


	//-------------------------------Search user invoice by mobileNumber (GET API) -----------------------------------------//

	public ResponseEntity<List<InvoiceTableDTO>> searchByMobile(String candidateMobile) {
		
		if(candidateMobile != null && !candidateMobile.isEmpty()) {
			
			log.info("searched invoice by mobile number",candidateMobile);
		    List<InvoiceTableDTO> invoices = invoiceTableRepository.searchInvoiceByMobile(candidateMobile);
		    return new ResponseEntity<>(invoices, HttpStatus.OK);		
		}
		else {
			
			log.info("-----> invoice not found... <----");
	        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
		}
	}
	
	//-----------------------------------------------------------------------------------------//

}
