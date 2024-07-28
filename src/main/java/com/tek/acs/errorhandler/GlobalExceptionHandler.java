package com.tek.acs.errorhandler;

import com.tek.acs.util.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseStructure> handleNoSuchElement(NoSuchElementException ex)
    {															//is predefined exception
        ResponseStructure responseStructure=new ResponseStructure();
        responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
        responseStructure.setHttpStatus(HttpStatus.NOT_FOUND);
        responseStructure.setData(ex.getMessage()); //the message written inside the constructor of NoSuchElementException will be shown

        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.NOT_FOUND);

    }
}
