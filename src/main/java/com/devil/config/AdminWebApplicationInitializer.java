package com.devil.config;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AdminWebApplicationInitializer
extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // ContextLoaderListener 가 사용할 java config 클래스를 리턴한다.
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // DispatcherServlet 이 사용할 java config 클래스를 리턴한다.
    return new Class<?>[] {AdminWebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/admin/*"};
  }

  @Override
  protected String getServletName() {
    return "admin";
  }

  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] {
        new CharacterEncodingFilter("UTF-8")
    };
  }
}






