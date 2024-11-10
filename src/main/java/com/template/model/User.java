package com.template.model;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name ="organization_name", nullable = false)
    private String organizationName;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name="Logo",columnDefinition = "LONGBLOB")
    private byte[] Logo;
    
    @Column(name="subscription_plan")
    private String subscriptionPlan;

    
    //----- relatioship mapping -----//
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FormTemplate> formTemplates;
    
    public User() {}

	public User(Long id, String fullName, String address, String mobileNumber, String email, String organizationName,
			String userName, String password, byte[] logo, String subscriptionPlan, Set<FormTemplate> formTemplates) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.organizationName = organizationName;
		this.userName = userName;
		this.password = password;
		Logo = logo;
		this.subscriptionPlan = subscriptionPlan;
		this.formTemplates = formTemplates;
	}
    
    
}
