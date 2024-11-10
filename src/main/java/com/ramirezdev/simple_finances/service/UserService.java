package com.ramirezdev.simple_finances.service;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.domain.user.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserReposiroty userReposiroty;

    public Long findUserById(Long id) {
        var user = userReposiroty.getReferenceById(id);
        return user.getId();
    }

    public User findUser(Long id) {
        return userReposiroty.getReferenceById(id);
    }




}
