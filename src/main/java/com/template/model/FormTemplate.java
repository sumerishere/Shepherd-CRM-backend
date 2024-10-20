package com.template.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;


import com.fasterxml.jackson.databind.JsonNode;
import com.jsonConverter.JsonNodeConverter;

@Entity
@Table(name = "form_templates")
@Data
public class FormTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "form_name(Organization name)", nullable = false)
    private String formName;
    
    @Column(name = "createdAt(Date_Time)", nullable = true)
    private LocalDateTime createdAt;

    @Convert(converter = JsonNodeConverter.class)
    @Column(columnDefinition = "json", nullable = false)
    private JsonNode fields;
    
    private String password;
  
    
    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    private User user;

}
