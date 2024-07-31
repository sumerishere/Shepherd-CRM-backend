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
	
    @ManyToOne
    @JoinColumn(name = "template_id", referencedColumnName = "id", nullable = false)
    private FormTemplate formTemplate;
	
    
	
}
