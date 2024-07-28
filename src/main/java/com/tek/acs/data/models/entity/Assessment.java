package com.tek.acs.data.models.entity;

import com.tek.acs.data.models.enums.LEVEL;
import com.tek.acs.data.models.enums.STATUS;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "assessment")
public class Assessment {

    @Id
    private String assessmentId;
    private String assessmentname;
    private LocalDate enrolledDate;
    private LocalDateTime scheduledTime;
    private LEVEL level; //level is an enum
    private String progress;
    private String status;
    private Boolean isAttended;
    private Slot slot;
}
