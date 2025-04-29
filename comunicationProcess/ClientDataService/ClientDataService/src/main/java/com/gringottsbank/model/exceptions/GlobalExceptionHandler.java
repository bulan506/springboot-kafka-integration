package com.gringottsbank.model.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private record ErrorResponse(String msg, int status) {
    }
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        return ResponseEntity
                .status(ex.getErrorCode())
                .body(new ErrorResponse(
                        ex.getMessage(),
                        ex.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(new ErrorResponse(
                        "INTERNAL SERVER ERROR",
                        500));
    }

}
