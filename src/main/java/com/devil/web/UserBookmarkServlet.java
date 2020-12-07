package com.devil.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.UserService;

@WebServlet("/article/bookmark")
public class UserBookmarkServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService =
        (UserService) ctx.getAttribute("userService");

    Map<String, Object> map = new HashMap<>();
    map.put("loginUserNo", ((User)request.getSession().getAttribute("loginUser")).getNo());
    map.put("articleNo", request.getParameter("articleNo"));

    try {
      if (userService.bookmark(map) == 0) {
        throw new Exception("이미 팔로우하고 있는 태그입니다.");
      }
      response.sendRedirect("../tag/list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
