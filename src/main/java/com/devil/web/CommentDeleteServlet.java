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

@WebServlet("/comment/delete")
public class CommentDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    CommentService commentService = (CommentService) ctx.getAttribute("commentService");

    Comment comment = new Comment();
    comment.setArticleNo(Integer.parseInt(request.getParameter("arno")));
    comment.setContent(request.getParameter("content"));
    try {
      int no = Integer.parseInt(request.getParameter("no"));

      if (commentService.delete(no) == 0) {
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

