package com.blogger.service.impl;

import com.blogger.controller.base.BaseDao;
import com.blogger.controller.base.impl.BaseServiceImpl;
import com.blogger.dao.MenuDao;
import com.blogger.model.Menu;
import com.blogger.model.MenuTree;
import com.blogger.model.request.MenuRequest;
import com.blogger.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuRequest,Integer> implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public BaseDao getDao() {
        return menuDao;
    }

    @Override
    public List<MenuRequest> findAllMenu(MenuRequest t) {
        List<MenuRequest> responses = menuDao.selectListByPage(t);

        return responses;
    }

    @Override
    public List<MenuTree> getMenuTree(MenuRequest t) {
        List<MenuTree> menuList = menuDao.getMenuNotSuper();
        for (MenuTree m : menuList){
           getChild(m, m.getModule_id());
        }

        return menuList;
    }

    private List<MenuTree> getChild(MenuTree m, Integer id){
        List<MenuTree> sysMenu=menuDao.getMenuChildrenAll(id);
        for(MenuTree menu: sysMenu){
            getChild(menu, menu.getModule_id());
            m.addChild(menu);
        }
        return sysMenu;
    }
}
