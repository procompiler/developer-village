package com.devil.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.TagService;

@WebServlet("/auth/loginUser")
public class LoginUserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    TagService tagService = (TagService)request.getServletContext().getAttribute("tagService");
    try {
      List<Tag> tags = tagService.list((User)request.getSession().getAttribute("loginUser"));
      request.setAttribute("tags", tags);
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
    request.getRequestDispatcher("/auth/loginUser.jsp").include(request, response);

  }
}
