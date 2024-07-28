package com.tek.acs.data.models.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private String createdByName;
    private String createdByEmail;
    private String createdOn;

    private String modifiedByName;
    private String modifiedByEmail;
    private String modifiedOn;

    public void createBaseEntity(String name,String email)
    {
        createdByName=name;
        createdByEmail=email;
        createdOn= LocalDateTime.now().toString();
    }
    public void modifiedBaseEntity(String name,String email)
    {
        modifiedByName=name;
        modifiedByEmail=name;
        modifiedOn= LocalDateTime.now().toString();
    }
}
