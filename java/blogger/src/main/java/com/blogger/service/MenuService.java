package com.blogger.service;

import com.blogger.controller.base.BaseService;
import com.blogger.model.MenuTree;
import com.blogger.model.request.MenuRequest;

import java.util.List;

public interface MenuService extends BaseService<MenuRequest,Integer>{

    public List<MenuRequest> findAllMenu(MenuRequest t);
    public List<MenuTree> getMenuTree(MenuRequest t);
}
