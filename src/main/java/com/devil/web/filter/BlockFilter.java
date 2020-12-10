package com.devil.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Block;
import com.devil.domain.User;
import com.devil.service.BlockService;

// 필터 역할:
// - 차단된 유저일 경우 게시글/댓글 등록시 경고창을 띄운다.
//
@WebFilter(value={"/article/form", "/comment/add"})
public class BlockFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain)
          throws IOException, ServletException {
    try {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpServletResponse httpResponse = (HttpServletResponse) response;

      BlockService blockService =
          (BlockService) request.getServletContext().getAttribute("blockService");
      User loginUser = (User) httpRequest.getAttribute("loginUser");
      System.out.println(loginUser.getNickname());

      Block block = blockService.get(loginUser);
      System.out.println(block);

      // select
      if (block != null) {
        httpResponse.sendRedirect("/block/info");
        return;
      }

      chain.doFilter(httpRequest, httpResponse);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
