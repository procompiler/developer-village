package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Follow;
import com.devil.domain.User;
import com.devil.service.FollowService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/follow/user/add")
public class FollowAddUserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    FollowService followService =
        (FollowService) ctx.getAttribute("followService");

    User loginUser = (User) request.getSession().getAttribute("loginUser");
    Follow follow = new Follow()
        .setUserNo(loginUser.getNo())
        .setFolloweeNo(Integer.parseInt(request.getParameter("uno")));

    try {
      if (followService.addUser(follow) == 0) {
        throw new Exception("이미 팔로우하고 있는 유저입니다.");
      }
      response.sendRedirect(request.getHeader("Referer"));

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
