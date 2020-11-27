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
import com.devil.domain.Tag;
import com.devil.service.TagService;

@WebServlet("/tag/add")
public class TagAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    TagService tagService = (TagService) ctx.getAttribute("tagService");

    request.setCharacterEncoding("UTF-8");

    Tag tag = new Tag();
    tag.setName(request.getParameter("title"));
    tag.setColor(request.getParameter("content"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    // 웹브라우저 제목에 출력될 내용
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='2;url=list'>");
    out.println("<title>태그 등록</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>태그 등록</h1>");

      request.setCharacterEncoding("UTF-8");
      tag.setName(request.getParameter("name"));
      tag.setColor(request.getParameter("color").split("#")[1]);
      tag.setPhoto(request.getParameter("photo"));

      tagService.add(tag);

      out.println("태그를 등록했습니다.");

    } catch (Exception e) {
      out.printf("<p>작업 처리 중 오류 발생! - %s</p>\n", e.getMessage());
      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));
      out.printf("<pre>%s</pre>\n", errOut.toString());
    }
    out.println("</body>");
    out.println("</html>");
  }
}