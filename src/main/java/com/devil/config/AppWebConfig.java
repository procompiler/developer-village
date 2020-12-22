package com.devil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;
import com.devil.service.UserService;
import com.devil.web.interceptor.AuthInterceptor;
import com.devil.web.interceptor.AutoLoginInterceptor;

@ComponentScan("com.devil.web.app")
@EnableWebMvc
public class AppWebConfig implements WebMvcConfigurer {

  @Autowired
  UserService userService;

  @Bean
  public ViewResolver viewResolver() {
    return new InternalResourceViewResolver("/WEB-INF/jsp/app/", ".jsp");
  }

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver mr = new CommonsMultipartResolver();
    mr.setMaxUploadSize(10000000);
    mr.setMaxInMemorySize(2000000);
    mr.setMaxUploadSizePerFile(5000000);
    return mr;
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    UrlPathHelper helper = new UrlPathHelper();
    helper.setRemoveSemicolonContent(false);
    configurer.setUrlPathHelper(helper);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 자동 로그인을 수행하는 인터셉터 삽입
    registry.addInterceptor(new AutoLoginInterceptor(userService));

    // 모든 "/app/*" 요청에 대해 로그인 여부를 검사하는 인터셉터 삽입
    registry.addInterceptor(new AuthInterceptor());
  }
}


