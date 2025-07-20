package com.becxagy.book.api.infra.out.persistence.jpa.converter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.becxagy.book.api.core.domain.checklist.ExigenceEnum;

class ExigenceEnumConverterTest {

    private ExigenceEnumConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ExigenceEnumConverter();
    }

    @Test
    void shouldConvertEnumToDatabaseColumn() {
        // Given
        ExigenceEnum exigence = ExigenceEnum.OBRIGATORIO;

        // When
        String result = converter.convertToDatabaseColumn(exigence);

        // Then
        assertEquals("OBRIGATORIO", result);
    }

    @Test
    void shouldConvertNullEnumToNullString() {
        // When
        String result = converter.convertToDatabaseColumn(null);

        // Then
        assertNull(result);
    }

    @Test
    void shouldConvertDatabaseColumnToEnum() {
        // Given
        String value = "OPCIONAL";

        // When
        ExigenceEnum result = converter.convertToEntityAttribute(value);

        // Then
        assertEquals(ExigenceEnum.OPCIONAL, result);
    }

    @Test
    void shouldConvertEmptyStringToNull() {
        // When
        ExigenceEnum result = converter.convertToEntityAttribute("");

        // Then
        assertNull(result);
    }

    @Test
    void shouldConvertNullStringToNull() {
        // When
        ExigenceEnum result = converter.convertToEntityAttribute(null);

        // Then
        assertNull(result);
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        // Given
        String invalidValue = "INVALID_VALUE";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convertToEntityAttribute(invalidValue);
        });
    }

    @Test
    void shouldConvertAllValidEnumValues() {
        // Test all enum values
        for (ExigenceEnum enumValue : ExigenceEnum.values()) {
            // Convert to database
            String dbValue = converter.convertToDatabaseColumn(enumValue);
            assertEquals(enumValue.name(), dbValue);

            // Convert back to enum
            ExigenceEnum convertedEnum = converter.convertToEntityAttribute(dbValue);
            assertEquals(enumValue, convertedEnum);
        }
    }
}
