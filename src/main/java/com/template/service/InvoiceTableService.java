package com.template.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.template.model.InvoiceTable;
import com.template.repository.InvoiceTableRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class InvoiceTableService {
	
	@Autowired
	InvoiceTableRepository invoiceTableRepository;
	
	@Autowired
	JavaMailSender sender;
	
	
	//----------------------------------- invoice mail api ---------------------------------------//
	
	public void sendInvoiceMail(String to, String candidateName,Long invoiceId) throws MessagingException {
		
		 // Fetch the InvoiceTable entry by ID
        InvoiceTable invoice = invoiceTableRepository.findById(invoiceId)
                                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        
        // Get the PDF as byte[]
        byte[] pdfBytes = invoice.getInvoicePdf();
		
		
	 	String subject = "Fees Acknowledgement!!!, Testing Shastra.";

	    // Create MimeMessage and MimeMessageHelper
	    MimeMessage mime = sender.createMimeMessage();
	    MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true); // 'true' indicates multipart message
	    mimeHelper.setTo(to);
	    mimeHelper.setSubject(subject);

	    // HTML content with embedded image using cid for Instagram,LinkedIn and youtube icons.
	    String body = "<html><body>"
	            + "Hi " + candidateName + ","
	            + "<br><br>Thank you for joining Testing Shastra!"
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

	    // Attach the course image
	    FileSystemResource adminLogogRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\Admin-img\\logo.png"));
	    mimeHelper.addInline("adminLogo", adminLogogRes); // 'courseImage' is the Content ID (cid)

	    // Attach Instagram image icon
	    FileSystemResource instaRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\social-media-imgs\\insta-png.webp"));
	    mimeHelper.addInline("instaImage", instaRes); // 'instaImage' is the Content ID (cid)
	    
	    // Attach LinkedIn image icon
	    FileSystemResource linkedinRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\social-media-imgs\\linkedln-png.webp"));
	    mimeHelper.addInline("linkedinImage", linkedinRes); // 'linkedinImage' is the Content ID (cid)
	    
	    FileSystemResource youtubeRes = new FileSystemResource(new File("C:\\Users\\SUMER KHAN\\OneDrive\\Desktop\\Sheperd-react\\Shepherd\\public\\social-media-imgs\\youtube-png.webp"));
	    mimeHelper.addInline("youtubeImage", youtubeRes);
	    
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
	    invoiceTableRepository.save(invoiceTable);
	    return new ResponseEntity<>("Invoice data saved", HttpStatus.CREATED);
	}

	
	//-----------------------------------------------------------------------------------------//
	
	
	
	

}
