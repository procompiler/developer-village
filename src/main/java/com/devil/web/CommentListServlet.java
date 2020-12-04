package com.devil.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Article;
import com.devil.domain.Comment;
import com.devil.service.ArticleService;

@WebServlet("/comment/list")
public class CommentListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");

    int no = Integer.parseInt(request.getParameter("no"));

    response.setContentType("text/html;charset=UTF-8");
    //PrintWriter out = response.getWriter();

    try {
      Article article = articleService.get(no);
      request.setAttribute("article", article);

      List<Comment> comments = article.getComments();
      request.setAttribute("comments", comments);

      // UI 출력을 JSP에게 맡긴다.
      request.getRequestDispatcher("/comment/list.jsp").include(request, response);

      /* jsp 파일로 댓글 목록 출력하도록 수정 완료하면 아래 내용은 지울것임
       * 출력 스트림도 지울것임

      out.println("<hr size='3'>");
      out.println("<h2>Comments</h2>");

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      if (comments != null) {
        for (Comment comment : comments) {
          out.printf("<form action='../comment/update?no=%d&articleNo=%d' method='post'>\n",
              comment.getNo(), article.getNo());
          out.printf("<input type='hidden' name='cno' value='%d'>\n", comment.getNo());
          out.printf("<input type='hidden' name='arno' value='%d'>\n", article.getNo());
          out.printf(
              "<img src='../upload/user/%s_40x40.jpg' alt='[%1$s]'><a href='../user/detail?no=%d'>%s</a>\n",
              comment.getWriter().getPhoto(), comment.getWriter().getNo(),
              comment.getWriter().getNickname());
          out.printf("<textarea name='content' style=\"height:30px;width:400px;\">%s</textarea>\n",
              comment.getContent());
          out.printf("%s", formatter.format(comment.getCreatedDate()));
          out.println("<button>수정</button>\n");
          out.printf(
              "<button type='button' class='btn-danger' onclick=\"location.href='../comment/delete?no=%d&articleNo=%d'\">삭제</button>\n",
              comment.getNo(), article.getNo());
          out.printf("%s\n", comment.getState() == 1 ? "삭제안됨" : "삭제됨");
          out.println("</form>\n");

          out.println("<form method='post' action='../comment/add'>");
          out.printf("<input type='hidden' name=\"arno\" value='%d' readonly><br>",
              article.getNo());
          out.printf("<input type='hidden' name=\"parno\" value='%d' readonly><br>",
              comment.getNo());
          out.println("<input type='text' name='content'><br>");
          out.println("<button>답글쓰기</button>");
          out.println("</form>");

          out.println("<hr color='gray'>");
        }

        out.println("<form method='post' action='../comment/add'>");
        out.printf("<input type='hidden' name=\"arno\" value='%d' readonly><br>", article.getNo());
        out.println("<input type='text' name='content'>");
        out.println("<button>댓글쓰기</button>");
        out.println("</form>");
      }

      out.println("</body>");


       */

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
