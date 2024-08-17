package com.converttomapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class StringToListConverter implements AttributeConverter<List<Long>, String> {
    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        return attribute != null ? attribute.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "{", "}")) : null;
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        return dbData != null ? Arrays.stream(dbData.replaceAll("[{}]", "").split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList()) : null;
    }
}
