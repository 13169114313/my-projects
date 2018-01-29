package com.blogger.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice(annotations = RestController.class)
public class CustomException implements HandlerExceptionResolver {
  private static Logger log= LoggerFactory.getLogger(CustomException.class);

  @Override
  @ExceptionHandler(Exception.class)
  public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse, Object o, Exception e) {
    ModelAndView mv=new ModelAndView("/error/error");
    if(e instanceof Exception){
      //处理拦截shiro 无权限
      mv.setViewName("/login");
      return mv;
    }
   e.printStackTrace();
    MyException myExecption=null;
    if(e instanceof MyException){
      myExecption=(MyException)e;

    }else{
      myExecption=new MyException("未知错误");
    }

    //错误信息
    String message=myExecption.getMessage();

    ModelAndView modelAndView=new ModelAndView();

    //将错误信息传到页面
    modelAndView.addObject("message",message);

    //指向到错误界面
    //modelAndView.setViewName("error");
    return mv;
  }
}
