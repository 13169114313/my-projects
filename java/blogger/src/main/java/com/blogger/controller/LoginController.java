package com.blogger.controller;

import com.blogger.controller.base.BaseController;
import com.blogger.model.request.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/admin")
@Api(value="登录管理")
public class LoginController extends BaseController{

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiIgnore
    public String login(){

        return "admin/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiIgnore
    public String index(){

        return "admin/index";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户登录")
    public String login(
            @ApiParam(value="登录实体", required =true)
            @RequestBody LoginRequest loginRequest
    ){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getUserName(), loginRequest.getPassword());
        token.setRememberMe(true);
        subject.login(token);


        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "退出")
    public String logout(LoginRequest loginRequest){
        return null;
    }

    @GetMapping(value = "/login")
    public String loginCheck(){
        Subject sub= SecurityUtils.getSubject();
        Boolean flag2 = sub.isRemembered();
        boolean flag = sub.isAuthenticated() || flag2;
        Session session = sub.getSession();
        if(flag){
            return "/main/main";
        }
        return "/login";
    }
}
