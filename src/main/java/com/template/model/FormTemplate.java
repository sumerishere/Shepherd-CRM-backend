package com.template.model;
//import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsonConverter.JsonNodeConverter;

@Entity
@Table(name = "form_templates")
public class FormTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "form_name", nullable = false)
    private String formName;
    
    private LocalDateTime createdAt;

    @Convert(converter = JsonNodeConverter.class)
    @Column(columnDefinition = "json", nullable = false)
    private JsonNode fields;
    
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "formTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    
    
    public FormTemplate(){}
    
	public FormTemplate(Long id, String formName,LocalDateTime createdAt, JsonNode fields) {
	
		this.id = id;
		this.formName = formName;         	 
		this.createdAt = createdAt; 	 
		this.fields = fields;          
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormName() {
		return formName;
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
	
	
	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}

	public Set<Comment> getComments() {
	     return comments;
	}

	public void setComments(Set<Comment> comments) {
	     this.comments = comments;
	}
	

}
