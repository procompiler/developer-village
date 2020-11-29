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
import javax.servlet.http.HttpSession;
import com.devil.domain.Badge;
import com.devil.domain.User;
import com.devil.service.BadgeService;

@WebServlet("/badge/add")
public class BadgeAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    BadgeService badgeService = (BadgeService) ctx.getAttribute("badgeService");

    request.setCharacterEncoding("UTF-8");

    Badge badge = new Badge();
    badge.setName(request.getParameter("name"));
    badge.setContent(request.getParameter("content"));

    HttpSession session = request.getSession();

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    // 웹브라우저 제목에 출력될 내용
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='2;url=list'>");
    out.println("<title>뱃지 등록</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>[뱃지 등록]</h1>");

      request.setCharacterEncoding("UTF-8");
      badge.setName(request.getParameter("name"));
      badge.setContent(request.getParameter("content"));
      badge.setCategoryNo(Integer.parseInt(request.getParameter("categoryNo")));
      User loginUser = (User) session.getAttribute("loginUser");
      if (loginUser != null) {
        badge.setWriter(loginUser);
        badgeService.add(badge);
        out.println("뱃지를 등록했습니다.");
      } else {
        out.println("로그인을 해주세요!");
      }

    }catch (Exception e) {
      out.printf("<p>작업 처리 중 오류 발생! - %s</p>\n", e.getMessage());
      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));
      out.printf("<pre>%s</pre>\n", errOut.toString());
    }
    out.println("</body>");
    out.println("</html>");
  }
}