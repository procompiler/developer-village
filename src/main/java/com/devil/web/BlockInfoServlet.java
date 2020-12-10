package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.BlockService;

@WebServlet("/block/info")
public class BlockInfoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    BlockService blockService = (BlockService) ctx.getAttribute("blockService");

    response.setContentType("text/html;charset=UTF-8");
    try {
      User loginUser = (User) request.getSession().getAttribute("loginUser");
      request.setAttribute("blockedUser", blockService.get(loginUser.getNo()));

      request.getRequestDispatcher("/block/info.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }

}
