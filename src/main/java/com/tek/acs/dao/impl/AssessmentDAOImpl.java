package com.tek.acs.dao.impl;

import com.tek.acs.dao.AssessmentDAO;
import com.tek.acs.dao.UserDAO;
import com.tek.acs.data.models.entity.Assessment;
import com.tek.acs.data.models.entity.User;
import com.tek.acs.repository.AssessmentRepository;
import com.tek.acs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class AssessmentDAOImpl implements AssessmentDAO {

    private final AssessmentRepository assessmentRepository;

    private final MongoTemplate mongoTemplate;

private final UserDAO userDAO;

    @Override
    public Assessment createAssesment(Assessment assessment) {

        return  assessmentRepository.save(assessment); //it returns assessment
    }

    @Override
    public List<Assessment> findAttendedAssessmentsByIds(List<String> assessmentIds) {

  //Using Query and MongoTemplate instead of, Custom Query which i have used in Repository coz writing custom query s difficult to write
       Query query=new Query();
        query.addCriteria(Criteria.where("assessmentId").in(assessmentIds).and("isAttended").is(true));
        List<Assessment> assessments = mongoTemplate.find(query, Assessment.class);
        return assessments;

  //     return assessmentRepository.findAttendedAssessmentsByIds(assessmentIds); //using custom mongodb query
    }

    @Override
    public List<Assessment> findByAssessmentname(String assessmentname) {

        //Using Query and MongoTemplate instead of, Custom Query which i have used in Repository coz writing custom query's difficult to write
        Query query=new Query();
        query.addCriteria(Criteria.where("assessmentname").is(assessmentname));//here the field name and varible name is name
        List<Assessment> assessments = mongoTemplate.find(query, Assessment.class);
             return assessments;
        //return assessmentRepository.findByAssessmentname(assessmentname);


    }

    @Override
    public Assessment findByid(String assessmentId) {
        return assessmentRepository.findById(assessmentId).orElseThrow(()->new NoSuchElementException("Assessment not found by id"));
    }                                                     //invoke orElseThrow to avoid making the return type as Optional

    @Override
    public List<Assessment> findByRegexAndUserId(String partialName, String userId) {

        User user = userDAO.findingUserById(userId);

        List<String> assessmentIds = user.getAssessmentIds();
        Query query=new Query();// used to build the MongoDB query.
        query.addCriteria(Criteria.where("assessmentId").in(assessmentIds) //filters the results to only include assessments with IDs that are in the assessmentIds list.
                .and("assessmentname").regex(partialName,"i"));// adds an additional filter to match the assessmentname field against the partialName parameter using a case-insensitive regex match. The i flag at the end of the regex makes the match case-insensitive.
        List<Assessment> assessments = mongoTemplate.find(query, Assessment.class);//executes the query using the mongoTemplate and retrieves a list of Assessment objects that match the criteria.
        return assessments;
        //builds a query to filter assessments by ID and partial name match, executes the query, and returns the resulting list of assessments.
    }


}
