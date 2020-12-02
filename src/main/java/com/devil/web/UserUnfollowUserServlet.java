package com.devil.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.UserService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/user/unfollowUser")
public class UserUnfollowUserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService =
        (UserService) ctx.getAttribute("userService");

    
    User loginUser = (User) request.getSession().getAttribute("loginUser");
    Map<String, Object> map = new HashMap<>();
    map.put("loginUserNo", loginUser.getNo());
    map.put("userNo", Integer.parseInt(request.getParameter("uno")));

    try {
      if (userService.unfollow(map) == 0) {
        throw new Exception("팔로우하지 않은 유저입니다.");
      } 
      loginUser.setUsers(userService.get(loginUser.getNo()).getUsers());
      response.sendRedirect("../user/list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
  }
}
