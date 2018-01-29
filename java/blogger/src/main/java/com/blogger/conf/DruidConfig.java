package com.blogger.conf;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new WebStatFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,*.html");
    return filterRegistrationBean;
  }

  @Bean
  public ServletRegistrationBean druidServlet() {
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
    servletRegistrationBean.setServlet(new StatViewServlet());
    servletRegistrationBean.addUrlMappings("/druid/*");
    Map<String, String> initParameters = new HashMap<String, String>();
    initParameters.put("resetEnable", "false");
    initParameters.put("allow", "");
    servletRegistrationBean.setInitParameters(initParameters);
    return servletRegistrationBean;
  }
}
