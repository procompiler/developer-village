package com.devil.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

  FilterConfig config;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    config = filterConfig;
  }

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain)
          throws IOException, ServletException {

    // 다음 필터나 서블릿을 실행하기 전에
    // 요청 파라미터의 값이 UTF-8 로 인코딩 되었음을 알린다.
    request.setCharacterEncoding(config.getInitParameter("encoding"));

    // 다음 필터 또는 서블릿을 실행한다.
    chain.doFilter(request, response);
  }
}
