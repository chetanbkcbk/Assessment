package com.tek.acs.controller;

import com.tek.acs.data.models.entity.Assessment;
import com.tek.acs.service.AssessmentService;
import com.tek.acs.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @PostMapping("/")
    public ResponseEntity<ResponseStructure> addAssessment(@RequestBody Assessment assessment)
    {
        return assessmentService.addAssessment(assessment);
    }

    @GetMapping ("/{userid}")// But find only if they have attended the assessment EX:- isAttended=True

    public ResponseEntity<ResponseStructure> findAssessmentByUserId(@PathVariable String userid)
    {       //But find only if they have attended the assessment EX:- isAttended=True
        return assessmentService.findAssessmentByUserId(userid);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseStructure> findByAssessmentName(@RequestParam String assessmentname)
    {
        return assessmentService.findByAssessmentName(assessmentname);
    }
    @GetMapping("/searchAssessments/{userId}")    //using regex in repository
    public ResponseEntity<ResponseStructure> searchAssessmentsByRegexAndUserId(@RequestParam String partialName,@PathVariable String userId) {
        return assessmentService.searchAssessmentsByRegexAndUserId(partialName,userId);
    }

}
