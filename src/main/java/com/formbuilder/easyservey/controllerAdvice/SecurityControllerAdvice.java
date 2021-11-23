package com.formbuilder.easyservey.controllerAdvice;


import com.formbuilder.easyservey.DataValidation.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class SecurityControllerAdvice {

    @ExceptionHandler(value = {SecurityException.class})
    protected ResponseEntity<Object> handleException(SecurityException e){

        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){
        ApiError error =new ApiError(400, exception.getMessage(), request.getServletPath());
        BindingResult bindingResult=exception.getBindingResult();
        Map<String,String> validationErrors=new HashMap<>();
        for (FieldError fieldError:bindingResult.getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        log.error("Error  : "+validationErrors.toString());
        error.setValidationErrors(validationErrors);
        return error;
    }
}
