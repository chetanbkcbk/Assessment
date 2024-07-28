package com.tek.acs.data.models.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class FeedBack extends BaseEntity {

    @Id
    private String id;

    private String assessmentId; // Reference to the assessment

    private String userId; // Reference to the user

    private int rating;

    private String comment;

}
