package com.blogger.dao;

import com.blogger.controller.base.BaseDao;
import com.blogger.model.Menu;
import com.blogger.model.MenuTree;
import com.blogger.model.request.MenuRequest;

import java.util.List;

public interface MenuDao extends BaseDao<MenuRequest,Integer> {
    List<MenuTree> getMenuNotSuper();
    List<MenuTree> getMenuChildren(Integer id);
    List<MenuTree> getMenuChildrenAll(Integer id);
}
