package com.template.formDataDTO;

import com.fasterxml.jackson.databind.JsonNode;

public class UpdateDataTableDTO {
	
	    private Long uid;
	    private JsonNode fieldsData;

	    // Getters and Setters
	    
	    public Long getUid() {
	        return uid;
	    }

	    public void setUid(Long uid) {
	        this.uid = uid;
	    }

	    public JsonNode getFieldsData() {
	        return fieldsData;
	    }

	    public void setFieldsData(JsonNode fieldsData) {
	        this.fieldsData = fieldsData;
	    }

}
