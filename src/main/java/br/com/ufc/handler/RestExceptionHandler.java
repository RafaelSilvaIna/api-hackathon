package br.com.ufc.handler;

import error.ResourceDuplicateDetails;
import error.ResourceDuplicateException;
import error.ResourceNotFoundDetails;
import error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
        resourceNotFoundDetails.setStatus(HttpStatus.NOT_FOUND.value());
        resourceNotFoundDetails.setTitle("Resource not found");
        resourceNotFoundDetails.setDetail(resourceNotFoundException.getMessage());
        resourceNotFoundDetails.setDeveloperMessage(resourceNotFoundException.getClass().getName());
        resourceNotFoundDetails.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public ResponseEntity<?> handleResourceDuplicateException(ResourceDuplicateException resourceDuplicateException) {
        ResourceDuplicateDetails resourceDuplicateDetails = new ResourceDuplicateDetails();
        resourceDuplicateDetails.setStatus(HttpStatus.CONFLICT.value());
        resourceDuplicateDetails.setTitle("Resource duplicate");
        resourceDuplicateDetails.setDetail(resourceDuplicateException.getMessage());
        resourceDuplicateDetails.setTimestamp(System.currentTimeMillis());
        resourceDuplicateDetails.setDeveloperMessage(resourceDuplicateException.getClass().getName());

        return new ResponseEntity<>(resourceDuplicateDetails, HttpStatus.CONFLICT);
    }
}
