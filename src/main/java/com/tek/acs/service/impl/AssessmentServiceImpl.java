package com.tek.acs.service.impl;

import com.tek.acs.dao.AssessmentDAO;
import com.tek.acs.dao.UserDAO;
import com.tek.acs.data.models.entity.Assessment;
import com.tek.acs.data.models.entity.User;
import com.tek.acs.repository.AssessmentRepository;
import com.tek.acs.repository.UserRepository;
import com.tek.acs.service.AssessmentService;
import com.tek.acs.util.ApiResponseStructure;
import com.tek.acs.util.CommonConstants;
import com.tek.acs.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;



    private final UserDAO userDAO;


    private final AssessmentDAO assessmentDAO;

    @Override
    public ResponseEntity<ResponseStructure> addAssessment(Assessment assessment) {
        Assessment assesment = assessmentDAO.createAssesment(assessment);

        return ApiResponseStructure.createResponse(assesment);
    }

 /*   @Override
    public ResponseEntity<ResponseStructure> findAssessmentByUserId(String userid) {
        User user = userRepository.findById(userid).get();
        List<String> assessmentIds = user.getAssessmentIds();
        for (String aid : assessmentIds)
        {
            Assessment assessment = assessmentRepository.findById(aid).get();
            if (assessment != null)
            {
                if (assessment.getIsAttended())
                {
                    return ApiResponseStructure.okResponse(assessment);
                }
                else{

                }
            }


        }
        return null;
    }

  */
 @Override  //But find only if they have attended the assessment EX:- isAttended=True

 public ResponseEntity<ResponseStructure> findAssessmentByUserId(String userid) {

   /*  this is using for each loop but is not efficient
     // Fetch the user by ID and handle if user not found
     User user = userRepository.findById(userid)
             .orElseThrow(() -> new NoSuchElementException("User not found with ID: " ));

     // Get the list of assessment IDs from the user
     List<String> assessmentIds = user.getAssessmentIds();

     // Loop through assessment IDs and find attended assessment
     for (String aid : assessmentIds) {
         Assessment assess = assessmentRepository.findById(aid)
                 .orElseThrow(() -> new NoSuchElementException("Assessment not found with ID: " ));

         // Check if the assessment is attended
         if (Boolean.TRUE.equals(assess.getIsAttended())) {
             return ApiResponseStructure.okResponse(assess);
         }
     }

     // If no attended assessment found, throw an exception or return an appropriate response
     throw new NoSuchElementException("No attended assessments found for user with ID: " );

*/
     User user = userDAO.findingUserById(userid);

     List<String> assessmentIds = user.getAssessmentIds();

     List<Assessment> attendedAssessmentsByIds = assessmentDAO.findAttendedAssessmentsByIds(assessmentIds);


    //DO THIS LATER  attendedAssessmentsByIds.stream().filter(Assessment::getIsAttended).



     // Check if any attended assessments are found
     if (!attendedAssessmentsByIds.isEmpty()) {
         return ApiResponseStructure.foundResponse(attendedAssessmentsByIds);
     }
     // If no attended assessment found, try to return an appropriate response
return ApiResponseStructure.badResponse(CommonConstants.NO_ATTENDEDASSESSMENT);
 }

    @Override
    public ResponseEntity<ResponseStructure> findByAssessmentName(String assessmentname) {
        List<Assessment> assessmtlist = assessmentDAO.findByAssessmentname(assessmentname);

        if (!CollectionUtils.isEmpty(assessmtlist)) { //Industry standard to check if any collection isempty
            return ApiResponseStructure.okResponse(assessmtlist);
        } else {
            return ApiResponseStructure.badResponse(CommonConstants.NO_ASSESSMENT);
        }
    }

    @Override
    public ResponseEntity<ResponseStructure> searchAssessmentsByRegexAndUserId(String partialName, String userId) {
        // Fetch the user's list of assessment IDs

     //   List<Assessment> matchingAssessments = assessmentRepository.findByAssessmentnameRegexAndIds(regex, assessmentIds); //this is using custom Query methods which is not suggested

        List<Assessment> matchingAssessments = assessmentDAO.findByRegexAndUserId(partialName, userId);
        if (!CollectionUtils.isEmpty(matchingAssessments)) { //Industry standard to check if any collection isempty
            return ApiResponseStructure.foundResponse(matchingAssessments);
        } else {
            return ApiResponseStructure.badResponse(CommonConstants.NO_ASSESSMENT);
        }
    }
    }












