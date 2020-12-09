package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.UserService;

@WebServlet("/mypage/followerlist")
public class MyFollowerListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    UserService userService = (UserService)request.getServletContext().getAttribute("userService");
    try {
      request.setAttribute("followerList", userService.listFollower((User)request.getSession().getAttribute("loginUser")));
      request.getRequestDispatcher("/mypage/followerlist.jsp").include(request, response);
      for (User user : userService.listFollower((User)request.getSession().getAttribute("loginUser"))) {
        System.out.println(user.getName());
      }
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
