package com.blogger.controller;

import com.blogger.controller.base.BaseController;
import com.blogger.core.MyException;
import com.blogger.model.Test;
import com.blogger.model.request.TestRequest;
import com.blogger.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author chen
 */
@Api(value="测试controller",tags={"测试接口"})
@RestController
public class TestController extends BaseController {

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String test(
            @ApiParam(value="查询实体", required = false)
            @RequestBody TestRequest test,
            @ApiParam(value="页数,页数从1开始", defaultValue="1")
            @RequestParam(defaultValue="1") int page,

            @ApiParam(value="每页数量",defaultValue="10")
            @RequestParam(defaultValue="10") int pageSize
    ){
        try{
            return testService.show(test, page,pageSize);
        }catch (Exception e){
            throw new MyException("获取列表失败::" + e.getMessage());
        }
    }
}
