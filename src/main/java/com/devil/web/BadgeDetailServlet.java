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

@WebServlet("/badge/detail")
public class BadgeDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Servlet container에 들어 있는 badgeService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    BadgeService badgeService = (BadgeService) ctx.getAttribute("badgeService");

    // 웹주소에 동봉된 데이터(Query String: qs)를 읽는다.
    // 클라이언트가 URL에 데이터를 포함해서 보낸다.
    // 숫자 데이터가 넘어오기 때문에 깨질 염려가 없다.
    int no = Integer.parseInt(request.getParameter("no"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head><title>뱃지 조회</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
    out.println("<body>");

    try {
      out.println("<h1>[뱃지 조회]</h1>");

      Badge badge = badgeService.get(no);

      if (badge == null) {
        out.println("해당 번호의 뱃지가 없습니다.");
        return;
      }

      out.println("<form action='update' method='post'>");
      out.printf("<input type='text' name='no' value='%d' readonly style='display:hidden;'><br>", badge.getNo());
      out.printf("<p>이름 : %s</p>", badge.getName());
      out.printf("<a href='../upload/user/%s'>\n<img src='../upload/badge/%1$s' alt='[%1$s]'></a><br>\n", badge.getPhoto());
      out.printf("<textarea name='content'>%s</textarea><br>\n", badge.getContent());

      out.println("<button>수정</button>");
      out.printf("<button type='button' class='btn-danger' onclick=\"location.href='delete?no=%d'\">삭제</button>", badge.getNo());
      out.println("</form>");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }


    out.println("</body>");
    out.println("</html>");
  }
}