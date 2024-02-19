package com.example.atm.project.validate;

import com.example.atm.project.Exception.DuplicateUserNameException;
import com.example.atm.project.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserValid {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String,String> handle(MethodArgumentNotValidException ex){
    Map<String,String> error=new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(err->{error.put(err.getField(),err.getDefaultMessage());});
    return error;
}
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> handle(UserNotFoundException ex){
    Map<String,String> error=new HashMap<>();
    error.put("errormessage",ex.getMessage());
    return error;
}
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateUserNameException.class)
    public Map<String,String> handle(DuplicateUserNameException ex){
        Map<String,String> error=new HashMap<>();
        error.put("errormessage",ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String,String> handle(HttpMessageNotReadableException ex){
        Map<String,String> error=new HashMap<>();
        error.put("ResponseCode ", Integer.toString(HttpStatus.BAD_REQUEST.value()));
        error.put("ErrorMessage ",ex.getMessage());
        return error;
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public Map<String,String> handle(NumberFormatException ex){
        Map<String,String> error=new HashMap<>();
        error.put("ResponseCode ", Integer.toString(HttpStatus.BAD_REQUEST.value()));
        error.put("ErrorMessage ",ex.getMessage());
        return error;
    }

}
















































