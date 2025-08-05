package com.example.textanalyzer.exception;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleInvalidType_ReturnsCorrectErrorResponse() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid analysis type: xyz");
        
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleInvalidType(exception);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid analysis type: xyz", response.getBody().get("error"));
    }

    @Test
    void handleValidationError_ReturnsCorrectErrorResponse() {
        ConstraintViolationException exception = new ConstraintViolationException("Text cannot be blank", null);
        
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleValidationError(exception);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().get("error").contains("Validation failed"));
        assertTrue(response.getBody().get("error").contains("Text cannot be blank"));
    }

    @Test
    void handleMissingParameter_ReturnsCorrectErrorResponse() {
        MissingServletRequestParameterException exception = 
            new MissingServletRequestParameterException("text", "String");
        
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleMissingParameter(exception);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Missing required parameter: text", response.getBody().get("error"));
    }

    @Test
    void handleGenericError_ReturnsInternalServerError() {
        Exception exception = new RuntimeException("Unexpected error");
        
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleGenericError(exception);
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred", response.getBody().get("error"));
    }

    @Test
    void allHandlers_ReturnMapWithErrorKey() {
        IllegalArgumentException illegalArg = new IllegalArgumentException("test");
        ConstraintViolationException validation = new ConstraintViolationException("test", null);
        MissingServletRequestParameterException missing = 
            new MissingServletRequestParameterException("param", "String");
        Exception generic = new Exception("test");

        assertTrue(exceptionHandler.handleInvalidType(illegalArg).getBody().containsKey("error"));
        assertTrue(exceptionHandler.handleValidationError(validation).getBody().containsKey("error"));
        assertTrue(exceptionHandler.handleMissingParameter(missing).getBody().containsKey("error"));
        assertTrue(exceptionHandler.handleGenericError(generic).getBody().containsKey("error"));
    }
}