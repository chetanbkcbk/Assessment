package com.tek.acs.service;

import com.tek.acs.data.models.entity.Assessment;
import com.tek.acs.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface AssessmentService {

    public ResponseEntity<ResponseStructure> addAssessment(Assessment assessment);

    public ResponseEntity<ResponseStructure> findAssessmentByUserId(String userid);

   public  ResponseEntity<ResponseStructure> findByAssessmentName(String assessmentname);


    ResponseEntity<ResponseStructure> searchAssessmentsByRegexAndUserId(String partialName, String userId);
}
