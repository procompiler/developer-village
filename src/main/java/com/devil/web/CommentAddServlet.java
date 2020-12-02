package com.devil.web;

import java.io.IOException;
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

    try {
      User user = (User) request.getSession().getAttribute("loginUser");
      comment.setWriter(user);
      commentService.add(comment);
      response.sendRedirect("../article/detail?no=");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
  }
}