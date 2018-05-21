package br.com.ufc.handler;

import br.com.ufc.error.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(TeamMembersLimitExceededException.class)
    public ResponseEntity<?> handleTeamMembersLimitExceededException(TeamMembersLimitExceededException teamMembersLimitExceededException) {
        TeamMembersLimitExceededDetails teamMembersLimitExceededDetails = new TeamMembersLimitExceededDetails();
        teamMembersLimitExceededDetails.setStatus(HttpStatus.CONFLICT.value());
        teamMembersLimitExceededDetails.setTitle("Member Limit Exceeded");
        teamMembersLimitExceededDetails.setDetail(teamMembersLimitExceededException.getMessage());
        teamMembersLimitExceededDetails.setTimestamp(System.currentTimeMillis());
        teamMembersLimitExceededDetails.setDeveloperMessage(teamMembersLimitExceededException.getClass().getName());
        teamMembersLimitExceededDetails.setLimiteParticipantsTeam(teamMembersLimitExceededException.getLimiteParticipantsTeam());

        return new ResponseEntity<>(teamMembersLimitExceededDetails, HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
        validationErrorDetails.setTimestamp(new Date().getTime());
        validationErrorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        validationErrorDetails.setTitle("Field Validation Error");
        validationErrorDetails.setDetail("Field Validation Error");
        validationErrorDetails.setDeveloperMessage(methodArgumentNotValidException.getClass().getName());
        validationErrorDetails.setField(fields);
        validationErrorDetails.setFieldMessage(fieldMessages);

        return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);
    }
}
