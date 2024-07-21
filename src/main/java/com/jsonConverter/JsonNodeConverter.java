package com.jsonConverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;


import java.io.IOException;

@Convert(attributeName = "true")
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String convertToDatabaseColumn(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JsonNode to String", e);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return objectMapper.readTree(dbData);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert String to JsonNode", e);
        }
    }
}
