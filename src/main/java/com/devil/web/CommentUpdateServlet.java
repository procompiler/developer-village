package com.devil.web;

import java.io.IOException;
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

    ServletContext ctx = request.getServletContext();
    CommentService commentService = (CommentService) ctx.getAttribute("commentService");

    try {
      Comment comment = new Comment();
      comment.setNo(Integer.parseInt(request.getParameter("cno")));
      comment.setArticleNo(Integer.parseInt(request.getParameter("arno")));
      comment.setContent(request.getParameter("content"));

      if (commentService.update(comment) == 0) {
        throw new Exception("해당 댓글이 없습니다.");
      }

      response.sendRedirect("../article/detail?no="+ comment.getArticleNo());

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }

  }
}
