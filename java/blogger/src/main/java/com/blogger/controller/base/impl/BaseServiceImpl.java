package com.blogger.controller.base.impl;

import com.alibaba.fastjson.JSON;
import com.blogger.controller.base.BaseDao;
import com.blogger.controller.base.BaseService;
import com.blogger.core.MyException;
import com.blogger.utils.ReType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.io.Serializable;

public abstract class BaseServiceImpl<T,E extends Serializable> implements BaseService<T,E> {

    public abstract BaseDao<T,E> getDao();

    @Override
    public String show(T t, int page, int limit){
        Page<T> pageList = PageHelper.startPage(page,limit);
        try{
            getDao().selectListByPage(t);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException("查询失败");
        }

        long total = pageList.getTotal();
        ReType reType = new ReType(total,pageList.getResult());

        return JSON.toJSONString(reType);
    }
}
