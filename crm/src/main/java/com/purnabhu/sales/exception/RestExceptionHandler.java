package com.purnabhu.sales.exception;
import com.purnabhu.sales.response.ResponseEntityObject;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntityObject.generateResponse("Validation Error", HttpStatus.BAD_REQUEST, ex.getBindingResult().toString());

    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex){
        return ResponseEntityObject.generateResponse("Entity not found", HttpStatus.NOT_FOUND,ex.getMessage());

    }
}