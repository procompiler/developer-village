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

      int no = Integer.parseInt(request.getParameter("no"));

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head><title>태그 조회</title>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
      out.println("<body>");

      try {
        out.println("<h1>태그 조회</h1>");

        Tag tag = tagService.get(no);

        if (tag == null) {
          out.println("해당 번호의 게시글이 없습니다.");
          return;
        }

        out.println("<form action='updatePhoto' method='post' enctype='multipart/form-data'>");
        out.printf("<input type='hidden' name='no' value='%s'>", tag.getNo());
        out.printf("<img src='../upload/tag/%s_120x120.png' alt='[%1$s]'>", tag.getPhoto());
        out.println("<input type='file' name='photo'><br>");
        out.println("<button>이미지 변경</button>");
        out.println("<form action='update' method='post'>");
        out.printf("<input type='hidden' name='no' value='%s'>", tag.getNo());
        out.printf("<p>태그색: <input type='color' name='tagColor' value='%s'></p>\n", tag.getTagColor());
        out.printf("<p>폰트색: <input type='color' name='fontColor' value='%s'></p>\n", tag.getFontColor());
        out.println("<button>태그 수정</button>");
        out.println("</form>");
        out.println("<p><a href='list' style='color:white;'>태그 목록으로</a></p>");
        out.printf("<button type='button'onclick=\"location.href='delete?no=%d'\">태그 삭제</button>", tag.getNo());
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


