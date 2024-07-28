package com.tek.acs.service.impl;

import com.tek.acs.dao.UserDAO;
import com.tek.acs.data.models.entity.User;
import com.tek.acs.service.UserService;
import com.tek.acs.util.ApiResponseStructure;
import com.tek.acs.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private final ModelMapper modelMapper;

   private final UserDAO userDAO;

    @Override
    public ResponseEntity<ResponseStructure> addUser(User user) {

                    user.createBaseEntity(user.getUsername(),user.getCreatedByEmail());
        User user1 = userDAO.addingUser(user);
        return ApiResponseStructure.createResponse(user1);
    }

    @Override
    public ResponseEntity<ResponseStructure> findAssessmentByUserId(String userid) {
        User user = userDAO.findingUserById(userid);
        return ApiResponseStructure.foundResponse(userid);

    }

    @Override
    public ResponseEntity<ResponseStructure> deleteByUserId(String userid) {
            userDAO.deletingByUserId(userid);
      return       ApiResponseStructure.deleteResponse("User deleted Successfully");
    }
}
