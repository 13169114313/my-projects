package com.blogger.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @create 2018-02-01 9:39
 **/
public class MenuTree extends Menu{
    private List<MenuTree> children = new ArrayList<>();

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    public void addChild(MenuTree sysMenu){
        children.add(sysMenu);
    }
}
