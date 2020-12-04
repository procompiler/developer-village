package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Badge;
import com.devil.service.BadgeService;

@WebServlet("/badge/update")
public class BadgeUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");

    // Servlet container에 들어 있는 BadgeService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    BadgeService badgeService = (BadgeService) ctx.getAttribute("badgeService");


    try {
      Badge badge = new Badge();
      badge.setNo(Integer.parseInt(request.getParameter("no")));
      badge.setName(request.getParameter("name"));
      badge.setContent(request.getParameter("content"));
      int count = badgeService.update(badge);

      if (count == 0) {
        throw new Exception("<p>해당 번호의 게시글이 없습니다.</p>");
      }
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
  }
}
