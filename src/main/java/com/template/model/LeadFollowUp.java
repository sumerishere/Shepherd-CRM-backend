package com.template.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.template.customIdGenerator.CustomIdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class LeadFollowUp {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom_id_generator")
    @GenericGenerator(name = "custom_id_generator",  type = CustomIdGenerator.class)
	private Long UID;
	
	@NotNull(message = "Name is required!")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	
	@Email(message ="email is required!")
	private String email;
	
	@Column(name="mobile_number")
	@NotNull(message= "mobile no. is required!")
	private String mobileNumber;
	private String address;
	private String courseType;
	private String source;
	private String referName;
	private String qualification;
	private String category;
	private String followUpDate;
	private String assignTo;
	private String statusType;
	
	private String createdAt;
	
	@OneToMany(mappedBy = "leadFollowUp", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference  //tell jackson to perform serialization mechanism on this entity and skip child serialization to avoid stackoverflow error and infinite recursion error.
	private List<Comment> comments = new ArrayList<>();
	
	
}
