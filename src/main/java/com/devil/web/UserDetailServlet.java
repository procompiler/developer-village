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
import com.devil.service.FollowService;
import com.devil.service.UserService;

@WebServlet("/user/detail")
public class UserDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");
    FollowService followService = (FollowService) ctx.getAttribute("followService");

    response.setContentType("text/html;charset=UTF-8");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      User user = userService.get(no);

      if (user == null) {
        throw new Exception("해당 번호의 유저가 없습니다!");
      }

      Map<String, Object> map = new HashMap<>();
      map.put("userNo", ((User)request.getSession().getAttribute("loginUser")).getNo());
      map.put("followeeNo", no);
      if (followService.getUser(map) != null) {
        request.setAttribute("followed", true);
      } else {
        request.setAttribute("followed", false);
      }

      request.setAttribute("user", user);
      request.getRequestDispatcher("/user/detail.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }

  }
}


