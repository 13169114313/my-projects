package com.blogger.dao.impl;

import com.blogger.controller.base.BaseMapper;
import com.blogger.controller.base.impl.BaseDaoImpl;
import com.blogger.dao.TestDao;
import com.blogger.mapper.TestMapper;
import com.blogger.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chen
 */
@Service
public class TestDaoImpl extends BaseDaoImpl implements TestDao{
    @Autowired
    private TestMapper testMapper;

    @Override
    public BaseMapper<Test, String> getMapper() {
        return testMapper;
    }

}
