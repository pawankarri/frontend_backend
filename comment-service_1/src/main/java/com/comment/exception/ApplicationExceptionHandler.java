package com.comment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(UserAlreadyLikedException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(UserAlreadyLikedException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_ACCEPTABLE);
        problemDetail.setProperty("message", ex.getMessage());
        problemDetail.setProperty("status message", "error");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(problemDetail);
    }
}
