package com.tek.acs.repository;

import com.tek.acs.data.models.entity.FeedBack;
import com.tek.acs.util.ResponseStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeedBackRepository extends MongoRepository<FeedBack,String> {



   //  Method to find the top 5 feedbacks sorted by rating
    List<FeedBack> findTop5ByOrderByRatingDesc();
}
