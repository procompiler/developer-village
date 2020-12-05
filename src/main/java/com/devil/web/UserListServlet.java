package com.devil.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.UserService;

@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");


    response.setContentType("text/html;charset=UTF-8");
    try {
      List<User> list = null;
      String keyword = request.getParameter("keyword");

      if(keyword != null) {
        list = userService.list(keyword);
      } else {
        list = userService.list((String) null);
      }
      request.setAttribute("followingUsers", userService.list((User)request.getSession().getAttribute("loginUser")));
      request.setAttribute("list", list);
      request.getRequestDispatcher("/user/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }

}
