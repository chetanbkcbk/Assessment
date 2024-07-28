package com.tek.acs.dao;

import com.tek.acs.data.models.entity.User;

import java.util.Optional;

public interface UserDAO {

    public User addingUser(User user);

   public  User findingUserById(String userid);

    void deletingByUserId(String userid);
}
