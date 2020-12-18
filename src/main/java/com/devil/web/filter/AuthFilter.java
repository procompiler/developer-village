package com.devil.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 필터 역할:
// - 로그인 하지 않은 경우 커맨드를 실행시키지 않는다.
//
//필터 역할:
//- 로그인 하지 않은 경우 커맨드를 실행시키지 않는다.
//
@WebFilter("/*")
public class AuthFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    if (httpRequest.getServletPath().startsWith("/auth") ||
        httpRequest.getSession().getAttribute("loginUser") != null) {
      chain.doFilter(request, response);
    } else {
      ServletContext servletContext = request.getServletContext();
      String contextRootPath = servletContext.getContextPath();
      httpResponse.sendRedirect(contextRootPath + "/auth/login");
    }
  }
}
