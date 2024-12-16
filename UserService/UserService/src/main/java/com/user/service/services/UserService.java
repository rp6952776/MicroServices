package com.user.service.services;

import com.user.service.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUserByUserId(String userId);
}