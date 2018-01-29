package com.blogger.dao.impl;

import com.blogger.dao.TestDao;
import com.blogger.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chen
 */
@Service
public class TestDaoImpl implements TestDao{
    @Autowired
    TestMapper testMapper;

    @Override
    public long findCount() {
        return testMapper.findCount();
    }
}
