package com.example.ems_project.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(EmployeeNotFoundException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex){
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFound(
            EmployeeNotFoundException ex, HttpServletRequest request){

    ErrorDetails e=new ErrorDetails(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
    );

    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);

    }

//    Add This Method (For Validation Errors)

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValudationExceptions(
            MethodArgumentNotValidException ex){

        Map<String, String> m=new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                e->m.put(e.getField(),e.getDefaultMessage()));
        return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
    }

}
