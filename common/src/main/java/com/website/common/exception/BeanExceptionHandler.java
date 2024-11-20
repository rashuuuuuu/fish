package com.website.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class BeanExceptionHandler {
    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        Map<String,Object> errors =new HashMap<>();
        for(FieldError fieldError:bindingResult.getFieldErrors()){
            errors.put("httpStatus", HttpStatus.NOT_ACCEPTABLE);
            errors.put("message",fieldError.getDefaultMessage());
            errors.put("code","-2");
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.badRequest().build();
    }
}
