package com.becxagy.book.api.infra.persistence.jpa.converter;

import com.becxagy.book.api.core.checklist.ExigenceEnum;

import jakarta.persistence.AttributeConverter;

public class ExigenceEnumConverter implements AttributeConverter<ExigenceEnum, String> {

    @Override
    public String convertToDatabaseColumn(ExigenceEnum exigenceEnum) {
        if (exigenceEnum == null) {
            return null;
        }
        return exigenceEnum.name();
    }

    @Override
    public ExigenceEnum convertToEntityAttribute(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return ExigenceEnum.valueOf(value);
    }
    
}
