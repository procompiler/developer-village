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
import com.devil.domain.Comment;
import com.devil.service.CommentService;

@WebServlet("/comment/update")
public class CommentUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Servlet container에 들어 있는 CommentService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    CommentService commentService = (CommentService) ctx.getAttribute("commentService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    Comment comment = new Comment();
    comment.setArticleNo(Integer.parseInt(request.getParameter("arno")));
    comment.setNo(Integer.parseInt(request.getParameter("cno")));
    comment.setContent(request.getParameter("content"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.printf("<meta http-equiv='Refresh' content='2;url=../article/detail?no=%d'>\n", comment.getArticleNo());
    out.println("<title>댓글 수정</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>댓글 수정</h1>");

      int count = commentService.update(comment);

      if (count == 0) {
        out.println("<p>해당 번호의 댓글이 없습니다.</p>");
      } else {
        out.println("<p>댓글을 수정하였습니다.</p>");
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
