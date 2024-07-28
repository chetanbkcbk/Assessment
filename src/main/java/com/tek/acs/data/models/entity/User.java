package com.tek.acs.data.models.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection="users")
public class User extends BaseEntity {

    @Id
    private String userid;
    private String username;
    private List<String>assessmentIds;
}
