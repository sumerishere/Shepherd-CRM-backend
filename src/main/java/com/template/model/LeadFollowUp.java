package com.template.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.template.customIdGenerator.CustomIdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class LeadFollowUp {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom_id_generator")
    @GenericGenerator(name = "custom_id_generator",  type = CustomIdGenerator.class)
	private Long UID;
	
	private String name;
	private String email;
	private String mobileNumber;
	private String address;
	
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "leadFollowUp", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();
	
	
}
