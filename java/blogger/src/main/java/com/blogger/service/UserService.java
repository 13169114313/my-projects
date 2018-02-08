package com.blogger.service;

import com.blogger.controller.base.BaseService;
import com.blogger.model.User;

public interface UserService extends BaseService{

    public User login(String userName);
}
