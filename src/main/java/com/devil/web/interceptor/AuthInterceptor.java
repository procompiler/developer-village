package com.devil.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (request.getPathInfo().startsWith("/auth") ||
        request.getPathInfo().startsWith("/main") ||
        request.getPathInfo().startsWith("/user/form") ||
        request.getPathInfo().startsWith("/user/add") ||
        request.getPathInfo().startsWith("/article/list") ||
        request.getPathInfo().startsWith("/tag/list") ||
        request.getSession().getAttribute("loginUser") != null) {
      return true; // 다음 인터셉터나 페이지 컨트롤러 실행!
    }

    ServletContext servletContext = request.getServletContext();
    String contextRootPath = servletContext.getContextPath();
    response.sendRedirect(contextRootPath + "/app/auth/login");
    return false;
  }

}
