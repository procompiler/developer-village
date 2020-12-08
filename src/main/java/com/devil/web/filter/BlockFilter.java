package com.devil.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 필터 역할:
// - 로그인 하지 않은 경우 커맨드를 실행시키지 않는다.
//
//@WebFilter(value={"/article/add", "/comment/add"})
public class BlockFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain)
          throws IOException, ServletException {
    System.out.println("AuthFilter 실행!");
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    /*
    BlockService blockService =
        (BlockService) request.getServletContext().getAttribute("blockService");
    Member loginUser = httpRequest.getSession().getAttribute("loginUser");
    Block block = blockService.get(로그인사용자번호);
    // select
    if (block != null) {
      httpResponse.sendRedirect("/block/info");
      return;
    }
    chain.doFilter(httpRequest, httpResponse);
     */

  }

}
