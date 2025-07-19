package com.becxagy.book.api.shared.utils.validation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024; // 20MB
    private static final List<String> ALLOWED_TYPES = List.of(
        "application/pdf", 
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );
    
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        
        if (file.getSize() > MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size must not exceed 10MB")
                   .addConstraintViolation();
            return false;
        }
        
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Only PDF and DOCX files are allowed")
                   .addConstraintViolation();
            return false;
        }
        
        return true;
    }
}