package com.template.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;
           
    private LocalDateTime createdAt;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_uid", referencedColumnName = "uid", nullable = false)
    @JsonBackReference
    private LeadFollowUp leadFollowUp;

    @Column(name = "lead_name")
    private String leadName;

    @PrePersist   // this is for get existing data from leadfollowup entity and store into comment enity as leadName
    public void prePersist() {
        if (leadFollowUp != null) {
            this.leadName = leadFollowUp.getName();
        }
    }
 
    
}