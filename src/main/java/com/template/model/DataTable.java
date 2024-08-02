package com.template.model;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsonConverter.JsonNodeConverter;
import com.template.customIdGenerator.CustomIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DataTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom_id_generator")
    @GenericGenerator(name = "custom_id_generator",  type = CustomIdGenerator.class)
    private Long UID;

    @Convert(converter = JsonNodeConverter.class)
    @Column(columnDefinition = "json", nullable = false)
    private JsonNode Fields_Data;
    
//    @Column(name = "image", columnDefinition = "LONGBLOB")
//    private byte[] image;
//
//    @Column(name = "pdf_files", columnDefinition = "LONGBLOB")
//    private byte[] pdfFiles;

    @ManyToOne
    @JoinColumn(name = "template_id", referencedColumnName = "id", nullable = false)
    private FormTemplate formTemplate;

    // Getter and Setter for Fields_Data
    public JsonNode getFieldsData() {
        return Fields_Data;
    }

    public void setFieldsData(JsonNode fieldsData) {
        this.Fields_Data = fieldsData;
    }

    // Getter and Setter for FormTemplate
    public FormTemplate getFormTemplate() {
        return formTemplate;
    }

    public void setFormTemplate(FormTemplate formTemplate) {
        this.formTemplate = formTemplate;
    }
    
    // Getter and Setter for Image
//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//
//    // Getter and Setter for PdfFiles
//    public byte[] getPdfFiles() {
//        return pdfFiles;
//    }
//
//    public void setPdfFiles(byte[] pdfFiles) {
//        this.pdfFiles = pdfFiles;
//    }
}
