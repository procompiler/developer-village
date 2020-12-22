package com.devil.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

// 로그인 여부를 검사하는 인터셉터
public class AuthInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (request.getPathInfo().startsWith("/auth") ||
        request.getSession().getAttribute("loginUser") != null) {
      return true; // 다음 인터셉터나 페이지 컨트롤러 실행!
    }

    ServletContext servletContext = request.getServletContext();
    String contextRootPath = servletContext.getContextPath();
    response.sendRedirect(contextRootPath + "/app/auth/login");
    return false;
  }

}
