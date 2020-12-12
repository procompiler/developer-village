package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Comment;
import com.devil.domain.User;
import com.devil.service.CommentService;

@Controller
public class CommentAddServlet {

  @RequestMapping("/comment/add")
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    CommentService commentService = (CommentService) ctx.getAttribute("commentService");

    Comment comment = new Comment();
    comment.setArticleNo(Integer.parseInt(request.getParameter("arno")));
    comment.setMotherNo(Integer.parseInt(request.getParameter("momno")));
    comment.setStep(Integer.parseInt(request.getParameter("step")));
    comment.setContent(request.getParameter("content"));

    try {
      User user = (User) request.getSession().getAttribute("loginUser");
      comment.setWriter(user);
      commentService.add(comment);
      response.sendRedirect("../article/detail?no="+ comment.getArticleNo());

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}