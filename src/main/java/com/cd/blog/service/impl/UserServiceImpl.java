package com.cd.blog.service.impl;

import com.cd.blog.dao.UserDao;
import com.cd.blog.entity.User;
import com.cd.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User checkUser(String username) {
        return userDao.findByUsername(username);
    }
}
