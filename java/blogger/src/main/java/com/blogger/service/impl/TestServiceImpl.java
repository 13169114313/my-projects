package com.blogger.service.impl;

import com.blogger.dao.TestDao;
import com.blogger.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chen
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    @Override
    public long findCount() {
        return testDao.findCount();
    }
}
