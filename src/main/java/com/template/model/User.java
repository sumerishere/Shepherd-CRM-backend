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

@Entity
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name", nullable = false)
    private String userName;
    
    @Column(name = "mobile_number", nullable = false)
    private String MobileNumber;

    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FormTemplate> formTemplates;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;
    
    // Constructors
    public User() {}

    public User(String userName, String MobileNumber, String email, String password) {
    	
        this.userName = userName;
        this.MobileNumber = MobileNumber;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
	public Set<FormTemplate> getFormTemplates() {
	    return formTemplates;
	}

	public void setFormTemplates(Set<FormTemplate> formTemplates) {
	    this.formTemplates = formTemplates;
	}

	public Set<Comment> getComments() {
	    return comments;
	}

	public void setComments(Set<Comment> comments) {
	    this.comments = comments;
	}
    
    
}