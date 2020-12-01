package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Tag;
import com.devil.service.TagService;

@WebServlet("/article/form")
public class ArticleAddFormServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    TagService tagService =
        (TagService) ctx.getAttribute("tagService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시글생성</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>게시글 생성</h1>");

      out.println("<form action='add' method='post'>");
      out.println("게시글명: <input type='text' name='title'><br>");
      out.println("내용: <textarea name='content' rows='10' cols='60'></textarea><br>");
      out.println("태그: <br>");
      out.println("<ul>");

      List<Tag> tags = tagService.list(null);
      for (Tag t : tags) {
        out.printf("<input type='checkbox' name='tags' value='%d'>%s  <br>\n",
            t.getNo(),
            t.getName());
      }

      out.println("<select name='categoryNo'>");
      out.println("<option value='1'>커뮤니티</option>");
      out.println("<option value='2'>QnA</option>");
      out.println("<option value='3'>채용공고</option>");
      out.println("<option value='4'>스터디</option>");
      out.println("</select><br>");
      
      out.println("<button>생성</button>");
      out.println("</form>");

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
