package com.blogger.service.impl;

import com.blogger.controller.base.BaseDao;
import com.blogger.controller.base.impl.BaseServiceImpl;
import com.blogger.dao.UserDao;
import com.blogger.model.User;
import com.blogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public BaseDao getDao() {
        return userDao;
    }

    @Override
    public User login(String userName) {
        User user = new User();
        user.setUsername("13");
        user.setPassword("13");
        return user;
    }
}
