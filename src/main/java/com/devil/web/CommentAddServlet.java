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
import com.devil.domain.User;
import com.devil.service.CommentService;
import com.devil.service.UserService;

@WebServlet("/comment/add")
public class CommentAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    CommentService commentService = (CommentService) ctx.getAttribute("commentService");
    UserService userService = (UserService) ctx.getAttribute("userService");

    Comment comment = new Comment();
    comment.setArticleNo(Integer.parseInt(request.getParameter("arno")));
    comment.setContent(request.getParameter("content"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.printf("<meta http-equiv='Refresh' content='2;url=../article/detail?no=%d'>\n", comment.getArticleNo());
    out.println("<title>댓글 등록</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>[댓글 등록]</h1>");
      User user = userService.get(1);
      //User loginUser = (User) session.getAttribute("loginUser");
      comment.setWriter(user);
      commentService.add(comment);
      out.println("댓글 등록했습니다.");
      //if (loginUser != null) {
      //} else {
      //  out.println("로그인을 해주세요!");
    //}

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }
}