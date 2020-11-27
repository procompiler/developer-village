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

@WebServlet("/tag/detail")
public class TagDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

      // Servlet container에 들어 있는 TagService를 꺼낸다.
      ServletContext ctx = request.getServletContext();
      TagService tagService = (TagService) ctx.getAttribute("tagService");

      // 웹주소에 동봉된 데이터(Query String: qs)를 읽는다.
      // 클라이언트가 URL에 데이터를 포함해서 보낸다.
      // 숫자 데이터가 넘어오기 때문에 깨질 염려가 없다.
      int no = Integer.parseInt(request.getParameter("no"));

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head><title>게시글 조회</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
      out.println("<body>");

      try {
        out.println("<h1>태그 조회</h1>");

        Tag tag = tagService.get(no);

        if (tag == null) {
          out.println("해당 번호의 게시글이 없습니다.");
          return;
        }

        out.printf("<p>태그명: %s</p>", tag.getNo());
        out.printf("<p>태그명: %s</p>", tag.getName());
        out.printf("<img src='../upload/tag/%s' alt='[%1$s]' height='100px'><br>", tag.getPhoto());
        out.printf("<p><span id='color' style='background-color:#%s'>%1$s</span></p>", tag.getColor());
        out.println("<p><a href='list' style='color:white;'>태그 목록으로</a></p>");

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


