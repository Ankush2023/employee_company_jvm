package com.epms.company.exception;

import com.epms.company.model.response.CustomResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExists(Exception e) {
        return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.OK);
    }
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<?> companyNotFound(Exception e) {
        return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.NOT_FOUND);
    }
}
