package com.template.formDataDTO;

import com.fasterxml.jackson.databind.JsonNode;

//formDataDTO

public class FormDataRequest {
    private Long formTemplateId;
    private JsonNode formData;
    
//    private byte[] image;      // Add this field
//    private byte[] pdfFiles;   // Add this field

    // Getters and Setters

    public Long getFormTemplateId() {
        return formTemplateId;
    }

    public void setFormTemplateId(Long formTemplateId) {
        this.formTemplateId = formTemplateId;
    }

    public JsonNode getFormData() {
        return formData;
    }

    public void setFormData(JsonNode formData) {
        this.formData = formData;
    }
    
//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//
//    public byte[] getPdfFiles() {
//        return pdfFiles;
//    }
//
//    public void setPdfFiles(byte[] pdfFiles) {
//        this.pdfFiles = pdfFiles;
//    }
}

