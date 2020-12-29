package com.devil.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import com.devil.domain.Block;
import com.devil.domain.User;
import com.devil.service.BlockService;

// 필터 역할:
// - 차단된 유저일 경우 게시글/댓글 등록시 경고창을 띄운다.
//
//@WebFilter(value={"/app/article/form", "/app/comment/add"})
public class BlockInterceptor implements HandlerInterceptor {

  BlockService blockService;

  public BlockInterceptor(BlockService blockService) {
    this.blockService = blockService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    User loginUser = (User) request.getSession().getAttribute("loginUser");
    System.out.println("블록필터 실행중!!!!");

    if (request.getPathInfo().startsWith("/article/form") ||
        request.getPathInfo().startsWith("/comment/add")) {
      Block block = blockService.getByUser(loginUser.getNo());

      if (block != null) {
        ServletContext servletContext = request.getServletContext();
        String contextRootPath = servletContext.getContextPath();
        response.sendRedirect(contextRootPath + "/app/block/info");
        return false;
      }
    }
    return true;
  }
}
