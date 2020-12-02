package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Servlet container에 들어 있는 ArticleService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    ArticleService articleService = (ArticleService) ctx.getAttribute("articleService");

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

      Article article = articleService.get(no);

      if (article == null) {
        out.println("해당 게시글이 없습니다.");
      } else {

        out.println("<form action='update' method='post'>");
        out.printf("<input type='hidden' name='no' value='%d'><br>", article.getNo());
        out.printf("<input type='text' name='title' value='%s'><br>", article.getTitle());
        out.printf("<p>작성자: %s</p>", article.getWriter().getNickname());

        int categoryNo = article.getCategoryNo();
        String categoryName = null;
        switch (categoryNo) {
          case 1: categoryName = "커뮤니티"; break;
          case 2: categoryName = "QnA"; break;
          case 3: categoryName = "채용공고"; break;
          default :categoryName = "스터디"; break;
        }

        out.printf("<p>카테고리: %s</p>", categoryName);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        out.printf("<p>등록일: %s</p>", formatter.format(article.getCreatedDate()));
        out.printf("<p>조회수: %d</p>", article.getViewCount());
        out.printf("<textarea name='content'>%s</textarea><br>\n", article.getContent());
        out.println("<button>수정</button>");
        out.printf("<button type='button' class='btn-danger' onclick=\"location.href='delete?no=%d'\">삭제</button>", article.getNo());
        out.println("<button><a href='../report/report-article.html'>게시글 신고</a></button>");
        out.println("</form>");

        out.println("<hr size='3'>");
        out.println("<h2>Comments</h2>");
        List<Comment> comments = article.getComments();
        if (comments != null) {
          for (Comment comment : comments) {
            out.printf("<form action='../comment/update?no=%d&articleNo=%d' method='post'>",
                comment.getNo(), article.getNo());
            out.printf("<input type='hidden' name='cno' value='%d'>\n",
                comment.getNo());
            out.printf("<input type='hidden' name='arno' value='%d'>\n",
                article.getNo());
            out.printf("<img src='../upload/user/%s_40x40.jpg' alt='[%1$s]'><a href='../user/detail?no=%d'>%s</a>\n",
                comment.getWriter().getPhoto(), comment.getWriter().getNo(), comment.getWriter().getNickname());
            out.printf("<textarea name='content' style=\"height:50px;width:340px;\">%s</textarea>\n", comment.getContent());
            out.printf("%s", formatter.format(comment.getCreatedDate()));
            out.println("<button>수정</button>\n");
            out.printf("<button type='button' class='btn-danger' onclick=\"location.href='../comment/delete?no=%d&articleNo=%d'\">삭제</button>", comment.getNo(), article.getNo());
            out.printf("%s\n", comment.getState() == 1 ? "삭제안됨" : "삭제됨");
            out.println("</form>\n");

            out.println("<form method='post' action='../comment/add'>"/*, comment.getNo()*/);
            out.printf("<input type='hidden' name=\"arno\" value='%d' readonly><br>", article.getNo());
            out.printf("<input type='hidden' name=\"parno\" value='%d' readonly><br>", comment.getNo());
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
      }

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
  }
}
