package com.blogger.controller;

import com.alibaba.fastjson.JSON;
import com.blogger.controller.base.BaseController;
import com.blogger.model.request.MenuRequest;
import com.blogger.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/menu")
@Api(value = "菜单管理")
public class MenuController extends BaseController
{
    @Autowired
    MenuService menuService;
    @RequestMapping(value = "/list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取菜单列表")
    public String menuList(MenuRequest request){

        return JSON.toJSONString(menuService.findAllMenu(request));
    }

    @RequestMapping(value = "/tree",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取菜单列表")
    public String menuTree(MenuRequest request){

        return JSON.toJSONString(menuService.getMenuTree(request));
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改菜单")
    public String update(){
        return "";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加菜单")
    public String add(){
        return "";
    }

    @RequestMapping(value = "/del",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除菜单")
    public String del(){
        return "";
    }

}
