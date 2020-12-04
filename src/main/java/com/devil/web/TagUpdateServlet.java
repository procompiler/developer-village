package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Tag;
import com.devil.service.TagService;

@WebServlet("/tag/update")
public class TagUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    // Servlet container에 들어 있는 TagService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    TagService tagService = (TagService) ctx.getAttribute("tagService");

    try {
      Tag tag = new Tag();
      tag.setNo(Integer.parseInt(request.getParameter("no")));
      tag.setName(request.getParameter("name"));
      tag.setTagColor(request.getParameter("tagColor"));
      tag.setFontColor(request.getParameter("fontColor"));
      int count = tagService.update(tag);



      if (count == 0) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
