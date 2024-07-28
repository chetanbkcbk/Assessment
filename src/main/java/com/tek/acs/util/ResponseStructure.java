package com.tek.acs.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseStructure {
    private int status;
    private HttpStatus httpStatus;
    private Object data;
}
