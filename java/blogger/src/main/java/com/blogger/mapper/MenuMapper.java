package com.blogger.mapper;

import com.blogger.controller.base.BaseMapper;
import com.blogger.model.Menu;
import com.blogger.model.MenuTree;
import com.blogger.model.response.MenuResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper{
    List<MenuTree> getMenuNotSuper();
    List<MenuTree> getMenuChildren(Integer id);
    List<MenuTree> getMenuChildrenAll(@Param("id") Integer id);
}
