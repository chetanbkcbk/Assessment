package com.tek.acs.service;

import com.tek.acs.data.models.entity.FeedBack;
import com.tek.acs.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeedBackService {

    public ResponseEntity<ResponseStructure> postFeedBack(FeedBack feedBack);

    ResponseEntity<ResponseStructure> findFeedBackByUserId(String userId);

    ResponseEntity<ResponseStructure> findTop5Feedbacks();
}
