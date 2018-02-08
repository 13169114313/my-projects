package com.blogger.dao.impl;

import com.blogger.controller.base.BaseMapper;
import com.blogger.controller.base.impl.BaseDaoImpl;
import com.blogger.dao.MenuDao;
import com.blogger.dao.UserDao;
import com.blogger.mapper.MenuMapper;
import com.blogger.mapper.UserMapper;
import com.blogger.model.Menu;
import com.blogger.model.MenuTree;
import com.blogger.model.request.MenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuDaoImpl extends BaseDaoImpl<MenuRequest,Integer> implements MenuDao {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseMapper getMapper() {
        return menuMapper;
    }
    @Override
    public List<MenuTree> getMenuNotSuper(){
        return menuMapper.getMenuNotSuper();
    }

    @Override
    public List<MenuTree> getMenuChildren(Integer id) {
        return menuMapper.getMenuChildren(id);
    }

    @Override
    public List<MenuTree> getMenuChildrenAll(Integer id) {
        return menuMapper.getMenuChildrenAll(id);
    }
}
