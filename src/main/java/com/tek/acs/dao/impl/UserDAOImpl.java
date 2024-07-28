package com.tek.acs.dao.impl;

import com.tek.acs.dao.UserDAO;
import com.tek.acs.data.models.entity.Assessment;
import com.tek.acs.data.models.entity.User;
import com.tek.acs.repository.AssessmentRepository;
import com.tek.acs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

//  private final MongoRepository mongoRepository;

  private final UserRepository userRepository;

  private final AssessmentRepository assessmentRepository;

  @Override
    public User addingUser(User user) {
        User user1 = userRepository.insert(user);
        return user1;
    }

    @Override
    public User findingUserById(String userid) {
      return userRepository.findById(userid)
                        .orElseThrow(()->new NoSuchElementException("User Not Found By Id"));
/* using orElseThrow() the Optional result is checked.
If a User is found, it is returned.
If no User is found, a NoSuchElementException is thrown with the specified message.*/
    }

  @Override
  public void deletingByUserId(String userid) {
      User user = userRepository.findById(userid).orElseThrow(() -> new NoSuchElementException("User Not Found By Id"));
     userRepository.delete(user);
      //Deletes the entity with the given id.
    //If the entity is not found in the persistence store it is silently ignored.

  }
}
