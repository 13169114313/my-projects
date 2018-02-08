package com.blogger.dao.impl;

import com.blogger.controller.base.BaseMapper;
import com.blogger.controller.base.impl.BaseDaoImpl;
import com.blogger.dao.UserDao;
import com.blogger.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper getMapper() {
        return userMapper;
    }
}
