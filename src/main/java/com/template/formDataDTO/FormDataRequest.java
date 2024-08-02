package com.template.formDataDTO;

import com.fasterxml.jackson.databind.JsonNode;

//formDataDTO

public class FormDataRequest {
    private Long formTemplateId;
    private JsonNode formData;

    // Getters and Setters

    public Long getFormTemplateId() {
        return formTemplateId;
    }

    public void setFormTemplateId(Long formTemplateId) {
        this.formTemplateId = formTemplateId;
    }

    public JsonNode getFormData() {
        return formData;
    }

    public void setFormData(JsonNode formData) {
        this.formData = formData;
    }
}

