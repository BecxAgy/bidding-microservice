package com.becxagy.book.api.shared.utils.validation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import jakarta.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
class FileValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder;

    private FileValidator fileValidator;

    @BeforeEach
    void setUp() {
        fileValidator = new FileValidator();
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
    }

    

    @Test
    void shouldRejectNullFile() {
        // When
        boolean result = fileValidator.isValid(null, context);

        // Then
        assertFalse(result);
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("File is required");
    }

    @Test
    void shouldRejectEmptyFile() {
        // Given
        MockMultipartFile emptyFile = new MockMultipartFile(
            "file", 
            "test.pdf", 
            "application/pdf", 
            new byte[0]
        );

        // When
        boolean result = fileValidator.isValid(emptyFile, context);

        // Then
        assertFalse(result);
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("File cannot be empty");
    }

    @Test
    void shouldRejectInvalidFileType() {
        // Given
        MockMultipartFile invalidFile = new MockMultipartFile(
            "file", 
            "test.txt", 
            "text/plain", 
            "test content".getBytes()
        );

        // When
        boolean result = fileValidator.isValid(invalidFile, context);

        // Then
        assertFalse(result);
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("Only PDF and DOCX files are allowed");
    }

    @Test
    void shouldRejectFileTooLarge() {
        // Given
        byte[] largeContent = new byte[25 * 1024 * 1024]; // 25MB
        MockMultipartFile largeFile = new MockMultipartFile(
            "file", 
            "large.pdf", 
            "application/pdf", 
            largeContent
        );

        // When
        boolean result = fileValidator.isValid(largeFile, context);

        // Then
        assertFalse(result);
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("File size must not exceed 20MB");
    }

    

    @Test
    void shouldRejectFileJustOverSizeLimit() {
        // Given
        byte[] overSizeContent = new byte[20 * 1024 * 1024 + 1]; // 20MB + 1 byte
        MockMultipartFile overSizeFile = new MockMultipartFile(
            "file", 
            "oversized.pdf", 
            "application/pdf", 
            overSizeContent
        );

        // When
        boolean result = fileValidator.isValid(overSizeFile, context);

        // Then
        assertFalse(result);
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate("File size must not exceed 20MB");
    }

    
}
