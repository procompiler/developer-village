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
import javax.servlet.http.HttpSession;
import com.devil.domain.User;
import com.devil.service.UserService;

// 필터 역할:
// - 로그인 하지 않은 경우 커맨드를 실행시키지 않는다.
//
@WebFilter("/*")
public class AuthFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession session = ((HttpServletRequest)request).getSession();
    if (httpRequest.getServletPath().startsWith("/auth") ||
        httpRequest.getServletPath().endsWith(".html") ||
        httpRequest.getServletPath().endsWith(".css") ||
        httpRequest.getServletPath().endsWith(".js") ||
        httpRequest.getServletPath().endsWith(".gif") ||
        httpRequest.getServletPath().endsWith(".jpg") ||
        httpRequest.getServletPath().endsWith(".jpeg") ||
        httpRequest.getServletPath().endsWith(".png") ||
        session.getAttribute("loginUser") != null) {
      UserService userService =
          (UserService) session.getServletContext().getAttribute("userService");
      User user;
      try {
        user = userService.get("abcd@gmail.com", "1111");
        session.setAttribute("loginUser", user);
      } catch (Exception e) {
        e.printStackTrace();
      }
      chain.doFilter(request, response);
    } else {
      ServletContext servletContext = request.getServletContext();
      String contextRootPath = servletContext.getContextPath();
      httpResponse.sendRedirect(contextRootPath + "/auth/login.html");
      System.out.println("로그인이 필요합니다.");
    }
  }

}
