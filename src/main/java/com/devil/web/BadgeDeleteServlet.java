package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.service.BadgeService;

@WebServlet("/badge/delete")
public class BadgeDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    BadgeService badgeService = (BadgeService) ctx.getAttribute("badgeService");

    response.setContentType("text/html;charset=UTF-8");
    try {

      int no = Integer.parseInt(request.getParameter("no"));
      if (badgeService.delete(no) == 0) {
        throw new Exception("<p>해당 번호의 뱃지가 없습니다.</p>");
      }
      response.sendRedirect("list");
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
  }
}

