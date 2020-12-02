package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Badge;
import com.devil.domain.Comment;
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
    out.println("<head><title>게시글 조회</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
    out.println("<body>");

    try {
      out.println("<h1>[게시물 조회]</h1>");

      Badge badge = badgeService.get(no);

      if (badge == null) {
        out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      out.println("<form action='update' method='post'>");
      out.printf("<input type='text' name='title' value='%s'><br>", badge.getTitle());
      out.printf("<input type='text' name='no' value='%d' readonly style='display:hidden;'><br>", badge.getNo());
      out.printf("<p>작성자: %s</p>", badge.getWriter().getNickname());


      int categoryNo = badge.getCategoryNo();
      String categoryName = null;
      switch (categoryNo) {
        case 1: categoryName = "커뮤니티"; break;
        case 2: categoryName = "QnA"; break;
        case 3: categoryName = "채용공고"; break;
        default :categoryName = "스터디"; break;
      }

      out.printf("<p>카테고리: %s</p>", categoryName);

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      out.printf("<p>등록일: %s</p>", formatter.format(badge.getCreatedDate()));
      out.printf("<p>조회수: %d</p>", badge.getViewCount());
      out.printf("<textarea name='content'>%s</textarea><br>\n", badge.getContent());
      out.println("<button>수정</button>");
      out.printf("<button type='button' class='btn-danger' onclick=\"location.href='delete?no=%d'\">삭제</button>", badge.getNo());
      out.println("</form>");
      out.println("<h3>Comments</h3>");
      List<Comment> comments = badge.getComments();
      if (comments != null) {
        for (Comment comment : comments) {
          out.printf("<img src='../upload/%s' alt='[%1$s]' height='100px'>", comment.getWriter().getPhoto());
          out.printf("<p><span id='color' style='background-color:black'>%s</span><br>", comment.getWriter().getNickname());

          out.printf("<t>%s</p>", comment.getContent());
        }

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