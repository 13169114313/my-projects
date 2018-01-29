package com.blogger.controller.base;

import com.alibaba.druid.support.json.JSONUtils;
import com.blogger.core.MyException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController<T> {

//  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
        new SimpleDateFormat("yyyy-MM-dd"), true));
  }

//  @ExceptionHandler
  public String authorizationException(HttpServletRequest request,HttpServletResponse res, Exception e1) {
    if (isAjaxRequest(request)) {
      try {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = res.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
        if (e1 instanceof MyException) {
          map.put("msg", e1.getMessage());
        } else {
          map.put("msg", "系统异常！");
        }
        writer.write(JSONUtils.toJSONString(map));
        writer.flush();
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    } else {
      String message="权限不足";
      try {
        message = URLEncoder.encode(message,"utf-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      return "redirect:/error/403?message="+message;
    }
  }
  public static boolean isAjaxRequest(HttpServletRequest request) {
    String requestedWith = request.getHeader("x-requested-with");
    if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
      return true;
    } else {
      return false;
    }
  }



}
