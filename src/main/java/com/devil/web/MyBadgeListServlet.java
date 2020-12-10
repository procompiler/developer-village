package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.BadgeService;

@WebServlet("/mypage/badgelist")
public class MyBadgeListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    BadgeService badgeService = (BadgeService)request.getServletContext().getAttribute("badgeService");
    try {
      request.setAttribute("badgeList", badgeService.list((User)request.getSession().getAttribute("loginUser")));
      request.getRequestDispatcher("/mypage/badgelist.jsp").include(request, response);
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
