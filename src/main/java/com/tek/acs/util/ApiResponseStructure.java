package com.tek.acs.util;

import com.tek.acs.data.models.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseStructure {

    private static ResponseStructure responseStructure=new ResponseStructure();

    public static ResponseEntity<ResponseStructure> createResponse(Object obj)
    {
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setHttpStatus(HttpStatus.CREATED);
        responseStructure.setData(obj);
        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.CREATED);
    }

    public static ResponseEntity<ResponseStructure> okResponse(Object obj)
    {
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setHttpStatus(HttpStatus.OK);
        responseStructure.setData(obj);
        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.OK);

    }
    public static ResponseEntity<ResponseStructure> foundResponse(Object obj)
    {
        responseStructure.setStatus(HttpStatus.FOUND.value());
        responseStructure.setHttpStatus(HttpStatus.FOUND);
        responseStructure.setData(obj);
        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.FOUND);

    }
    public static ResponseEntity<ResponseStructure> deleteResponse(Object obj)
    {
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setHttpStatus(HttpStatus.OK);
        responseStructure.setData(obj);
        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.OK);

    }
    public static ResponseEntity<ResponseStructure> badResponse(Object obj)
    {
        responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
        responseStructure.setHttpStatus(HttpStatus.NOT_FOUND);
        responseStructure.setData(obj);
        return new ResponseEntity<ResponseStructure>(responseStructure,HttpStatus.NOT_FOUND);

    }
}
