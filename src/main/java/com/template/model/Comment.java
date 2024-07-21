package com.template.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;
    
    private boolean isDeleted;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "form_template_id", nullable = false)
    private FormTemplate formTemplate;
    
    
    public Comment(){}

    public Comment(Long id, String comment, boolean isDeleted) {
		super();
		this.id = id;
		this.comment = comment;
		this.isDeleted = isDeleted;
	}

	// Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
	public User getUser() {
	    return user;
	}

    public void setUser(User user) {
	    this.user = user;
	}

	public FormTemplate getFormTemplate() {
	    return formTemplate;
	}

	public void setFormTemplate(FormTemplate formTemplate) {
	    this.formTemplate = formTemplate;
	}
    
}