package com.blogger.service.impl;

import com.blogger.controller.base.BaseDao;
import com.blogger.controller.base.BaseMapper;
import com.blogger.controller.base.BaseService;
import com.blogger.controller.base.impl.BaseServiceImpl;
import com.blogger.dao.TestDao;
import com.blogger.mapper.TestMapper;
import com.blogger.model.Test;
import com.blogger.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen
 */
@Service
public class TestServiceImpl extends BaseServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public BaseDao getDao() {
        return testDao;
    }
}
