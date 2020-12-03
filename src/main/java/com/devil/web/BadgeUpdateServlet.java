package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
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

    request.setCharacterEncoding("UTF-8");

    // Servlet container에 들어 있는 BadgeService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    BadgeService BadgeService = (BadgeService) ctx.getAttribute("BadgeService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='1;url=list'>");
    out.println("<title>게시글 변경</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>게시물 변경</h1>");

      Badge Badge = new Badge();
      Badge.setName(Integer.parseInt(request.getParameter("no")));
      Badge.setPhoto(request.getParameter("title"));
      Badge.setContent(request.getParameter("content"));
      int count = BadgeService.update(Badge);

      if (count == 0) {
        out.println("<p>해당 번호의 게시글이 없습니다.</p>");
      } else {
        out.println("<p>게시글을 변경하였습니다.</p>");
      }

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }

}
