package com.becxagy.book.api.infra.in.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        ValidationError err = new ValidationError(
            HttpStatus.UNPROCESSABLE_ENTITY.value(), 
            "Validation failed", 
            request.getRequestURI()
        );

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
            err.addError(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationError> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request) {
        
        ValidationError err = new ValidationError(
            HttpStatus.BAD_REQUEST.value(), 
            "Constraint validation failed", 
            request.getRequestURI()
        );

        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            err.addError(fieldName, message);
        });

        return ResponseEntity.badRequest().body(err);
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ValidationError> handleMaxUploadSizeExceeded(
            MaxUploadSizeExceededException ex, HttpServletRequest request) {
        
        ValidationError err = new ValidationError(
            HttpStatus.PAYLOAD_TOO_LARGE.value(), 
            "File upload failed", 
            request.getRequestURI()
        );

        err.addError("file", "File size exceeds maximum allowed size");
        
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(err);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ValidationError> handleMultipartException(
            MultipartException ex, HttpServletRequest request) {
        
        ValidationError err = new ValidationError(
            HttpStatus.BAD_REQUEST.value(), 
            "File upload failed", 
            request.getRequestURI()
        );

        err.addError("request", "Invalid multipart request format");
        
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ValidationError> handleMissingServletRequestPart(
            MissingServletRequestPartException ex, HttpServletRequest request) {
        
        ValidationError err = new ValidationError(
            HttpStatus.BAD_REQUEST.value(), 
            "Missing required file", 
            request.getRequestURI()
        );

        err.addError(ex.getRequestPartName(), "Required file parameter is missing");
        
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleGenericException(
            Exception ex, HttpServletRequest request) {
        
        StandardError err = new StandardError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            request.getRequestURI()
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
