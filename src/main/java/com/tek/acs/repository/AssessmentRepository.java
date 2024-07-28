package com.tek.acs.repository;

import com.tek.acs.data.models.entity.Assessment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends MongoRepository <Assessment,String>{
//Query Methods: Use method names to define queries. Spring Data interprets these method names and generates the appropriate queries.
//Custom Queries: Use @Query annotation for more complex queries that can't be expressed through query methods.

    // Query method
    List<Assessment> findByAssessmentname(String assessmentname);

    // below is "custom mongdb query" fetches assessments where:
    // - The 'id' field is in the list of provided assessment IDs
    // - The 'isAttended' field is true
    @Query("{ 'assessmentId': { $in: ?0 }, 'isAttended': true }")
    List<Assessment> findAttendedAssessmentsByIds(List<String> assessmentIds);


 /*   //To implement a search feature where typing a few letters of the assessment name returns matching results, you can use MongoDB's regular expression support.

    // Method to find assessments by partial name using regex
    @Query("{ 'assessmentname': { $regex: ?0, $options: 'i' } }")
    List<Assessment> findByAssessmentnameRegex(String regex);
}
*/


    // Method to find assessments by partial name using regex and a list of assessment IDs
    @Query("{ 'assessmentname': { $regex: ?0, $options: 'i' }, '_id': { $in: ?1 } }")
    List<Assessment> findByAssessmentnameRegexAndIds(String regex, List<String> assessmentIds);
}





