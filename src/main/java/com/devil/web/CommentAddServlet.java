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
import com.devil.domain.Comment;
import com.devil.domain.User;
import com.devil.service.CommentService;

@WebServlet("/comment/add")
public class CommentAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    CommentService commentService = (CommentService) ctx.getAttribute("commentService");

    request.setCharacterEncoding("UTF-8");

    Comment comment = new Comment();
    comment.setContent(request.getParameter("a"));
    comment.setContent(request.getParameter("content"));

    HttpSession session = request.getSession();

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    //out.println("<meta http-equiv='Refresh' content='2;url=list'>");
    out.println("<title>댓글 등록</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>[댓글 등록]</h1>");

      User loginUser = (User) session.getAttribute("loginUser");
      if (loginUser != null) {
        comment.setWriter(loginUser);
        commentService.add(comment);
        out.println("댓글 등록했습니다.");
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