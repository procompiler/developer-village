package com.devil.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.TagService;

@WebServlet("/tag/list")
public class TagListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ServletContext ctx = request.getServletContext();
    TagService tagService = (TagService) ctx.getAttribute("tagService");

    response.setContentType("text/html;charset=UTF-8");
    User loginUser = (User) request.getSession().getAttribute("loginUser");
    List<Integer> userTagNoList = new ArrayList<>();
    for (Tag tag : loginUser.getTags()) {
      userTagNoList.add(tag.getNo());
    }

    try {
      List<Tag> list = tagService.list(null);
      request.setAttribute("list", list);
      request.setAttribute("userTagNoList", userTagNoList);
      request.getRequestDispatcher("/tag/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
  }

}
