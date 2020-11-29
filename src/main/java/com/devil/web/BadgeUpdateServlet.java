package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
      Badge.setNo(Integer.parseInt(request.getParameter("no")));
      Badge.setTitle(request.getParameter("title"));
      Badge.setContent(request.getParameter("content"));
      int count = BadgeService.update(Badge);

      if (count == 0) {
        out.println("<p>해당 번호의 게시글이 없습니다.</p>");
      } else {
        out.println("<p>게시글을 변경하였습니다.</p>");
      }

    } catch (Exception e) {
      out.println("<h2>작업 처리 중 오류 발생!</h2>");
      out.printf("<pre>%s</pre>\n", e.getMessage());

      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));
      out.println("<h3>상세 오류 내용</h3>");
      out.printf("<pre>%s</pre>\n", errOut.toString());
    }

    out.println("</body>");
    out.println("</html>");
  }

}
