package com.template.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.JsonNode;


public class FormTemplateDTO {
	
	private Long id;
    private String formName;
    private LocalDateTime createdAt;
    private JsonNode fields;
    private String userName; // UserName

    // Getters and Setters
    
    
    
    public String getFormName() {
        return formName;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFormName(String formName) {
        this.formName = formName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public JsonNode getFields() {
        return fields;
    }

    public void setFields(JsonNode fields) {
        this.fields = fields;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
