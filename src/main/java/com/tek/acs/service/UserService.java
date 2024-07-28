package com.tek.acs.service;

import com.tek.acs.data.models.entity.User;
import com.tek.acs.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<ResponseStructure> addUser(User user);

    public  ResponseEntity<ResponseStructure> findAssessmentByUserId(String userid);

    ResponseEntity<ResponseStructure> deleteByUserId(String userid);
}
