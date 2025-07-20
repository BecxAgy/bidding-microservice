package com.becxagy.book.api.shared.utils.validation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 20L * 1024 * 1024; // 20MB
    private static final List<String> ALLOWED_TYPES = List.of(
        "application/pdf", 
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );
    
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        // Primeiro desabilita a violação padrão para usar mensagens customizadas
        context.disableDefaultConstraintViolation();
        
        if (file == null) {
            context.buildConstraintViolationWithTemplate("File is required")
                   .addConstraintViolation();
            return false;
        }
        
        if (file.isEmpty()) {
            context.buildConstraintViolationWithTemplate("File cannot be empty")
                   .addConstraintViolation();
            return false;
        }
        
        if (file.getSize() > MAX_FILE_SIZE) {
            context.buildConstraintViolationWithTemplate("File size must not exceed 20MB")
                   .addConstraintViolation();
            return false;
        }
        
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            context.buildConstraintViolationWithTemplate("Only PDF and DOCX files are allowed")
                   .addConstraintViolation();
            return false;
        }
        
        return true;
    }
}