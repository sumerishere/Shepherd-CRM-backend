package com.template.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class FormTemplateDTO {
    
    private Long id;
    private String formName;
    private LocalDateTime createdAt;  // Time
    private List<FieldDTO> fields;    // List of field objects
    private String userName;
    
    // Getters and Setters
    
    public String getFormName() {
        return formName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<FieldDTO> getFields() {
        return fields;
    }

    public void setFields(JsonNode fieldsNode) {
        this.fields = convertJsonToList(fieldsNode);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // Convert JsonNode to List<FieldDTO>
    private List<FieldDTO> convertJsonToList(JsonNode jsonNode) {
        List<FieldDTO> fieldList = new ArrayList<>();
        if (jsonNode != null && jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();
            long fieldId = 1; // Use long for consistency
            while (fieldsIterator.hasNext()) {
                Map.Entry<String, JsonNode> fieldEntry = fieldsIterator.next();
                String columnName = fieldEntry.getKey();
                String dataType = fieldEntry.getValue().asText();

                FieldDTO fieldDTO = new FieldDTO();
                fieldDTO.setColumnName(columnName);
                fieldDTO.setDataType(dataType);
                fieldDTO.setFieldId(fieldId++); // Ensure fieldId is of type Long
                
                fieldList.add(fieldDTO);
            }
        }
        return fieldList;
    }
    
    // Nested FieldDTO class
    public static class FieldDTO {
    	
        private String columnName;
        private String dataType;
        private Long fieldId;
        
        // Getters and Setters
        
        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public Long getFieldId() {
            return fieldId;
        }

        public void setFieldId(Long fieldId) {
            this.fieldId = fieldId;
        }
    }
}












//package com.template.model;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import com.fasterxml.jackson.databind.JsonNode;
//
//
//public class FormTemplateDTO {
//	
//	private Long id;
//    private String formName;
//    private LocalDateTime createdAt; 	//Time
////    private JsonNode fields;        	// JSON Object
//    private List<FormField> fields; // List of FormField objects
//    private String userName;   		
//  
//
//    // Getters and Setters
//    
//    public String getFormName() {
//        return formName;
//    }
//
//    public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public void setFormName(String formName) {
//        this.formName = formName;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public List<FormField> getFields() {
//        return fields;
//    }
//
//    public void setFields(List<FormField> fields) {
//        this.fields = fields;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//    
//    
//}
