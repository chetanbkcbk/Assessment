package com.tek.acs.data.models.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Slot {
//    private String slotId;
    private String startTime;
    private String endTime;
    private String userId;
    private String assessmentId;
    private String date;
}
