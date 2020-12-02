package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Badge;
import com.devil.service.BadgeService;

@WebServlet("/badge/list")
public class BadgeListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    BadgeService badgeService = (BadgeService) ctx.getAttribute("badgeService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head><title>뱃지목록</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
    out.println("<body>");

    try {
      out.println("<h1>뱃지 목록</h1>");
      out.println("<button type='button' onclick=\"location.href='form.html'\">뱃지 추가</button>");

      List<Badge> list = badgeService.list(null);

      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr>");
      out.println("<th>번호</th>");
      out.println("<th>뱃지이름</th>");
      out.println("<th>뱃지사진</th>");
      out.println("<th>뱃지내용</th>");
      out.println("</tr>");
      out.println("</thead>");
      out.println("<tbody>");



      for (Badge badge : list) {
        out.printf(
            "<tr>"
                + "<td>%d</td>"
                + "<td id=\"title\"><a href='detail?no=%1$d' style='color:white;'>%s</a></td>"
                + "<td><img style=\"width:80px\" src=\"../upload/badge/%s_20x20.jpg\"</td>"
                + "<td>%s</td>",
                badge.getNo(),
                badge.getName(),
                badge.getPhoto(),
                badge.getContent());

      }
      out.println("</tbody>");
      out.println("</table>");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }

}
