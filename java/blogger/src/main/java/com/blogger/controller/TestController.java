package com.blogger.controller;

import com.blogger.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testService;


    @RequestMapping("/test")
    @ResponseBody
    public String test(){

        return "this is test" + testService.findCount();
    }
}
