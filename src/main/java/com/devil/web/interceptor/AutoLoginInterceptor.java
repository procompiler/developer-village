package com.devil.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import com.devil.domain.User;
import com.devil.service.UserService;

public class AutoLoginInterceptor implements HandlerInterceptor {

  UserService userService;

  public AutoLoginInterceptor(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // 개발하는 동안 로그인을 자동으로 처리하기 위해 임의의 사용자로 로그인을 한다.
    if (request.getSession().getAttribute("loginUser") == null) {
      User loginUser = userService.get("abcd@gmail.com", "1111");
      request.getSession().setAttribute("loginUser", loginUser);
    }
    return true;
  }

}
