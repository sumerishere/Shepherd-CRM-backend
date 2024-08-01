package com.template.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "form_fields_data")
@Data
public class FormFieldData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field_id", nullable = false)
    private Integer fieldId;

    @Column(name = "data_type", nullable = false)
    private String dataType;

    @Column(name = "column_name", nullable = false)
    private String columnName;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private FormTemplate formTemplate;
}
