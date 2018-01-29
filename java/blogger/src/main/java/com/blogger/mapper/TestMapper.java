package com.blogger.mapper;

import com.blogger.controller.base.BaseMapper;
import com.blogger.model.Test;

import java.util.List;

/**
 * @author chen
 */
public interface TestMapper  extends BaseMapper<Test,String> {
    long findCount();

}
