package com.tek.acs.dao;

import com.tek.acs.data.models.entity.Assessment;

import java.util.List;
import java.util.Optional;

public interface AssessmentDAO {

    public Assessment createAssesment(Assessment assessment);

    List<Assessment> findAttendedAssessmentsByIds(List<String> assessmentIds);//using Query and MongoTemplate instead of Custom Query in Repository

    List<Assessment> findByAssessmentname(String assessmentname);//using Query and MongoTemplate instead of Custom Query in Repository

    public Assessment findByid(String assessmentId);

    public   List<Assessment>  findByRegexAndUserId(String partialName, String userId);//using Query and MongoTemplate instead of Custom Query in Repository
}
