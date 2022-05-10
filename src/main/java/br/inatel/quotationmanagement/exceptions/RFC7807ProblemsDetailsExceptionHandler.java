package br.inatel.quotationmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RFC7807ProblemsDetailsExceptionHandler {

    HttpStatus httpStatus;

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException e) {
        httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                new RFC7807ProblemsDetails("Resource not found", httpStatus.value(), "Requested resource does not exist"),
                httpStatus
        );
    }

    @ExceptionHandler(value = InvalidStockException.class)
    public ResponseEntity<?> invalidStockException(InvalidStockException e) {
        httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new RFC7807ProblemsDetails("Invalid Stock", httpStatus.value(), "The provided stock is invalid!"),
                httpStatus
        );
    }
}
