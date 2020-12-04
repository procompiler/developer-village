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

@WebServlet("/tag/detail")
public class TagDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

      // Servlet container에 들어 있는 TagService를 꺼낸다.
      ServletContext ctx = request.getServletContext();
      TagService tagService = (TagService) ctx.getAttribute("tagService");
      response.setContentType("text/html;charset=UTF-8");

      try {
        int no = Integer.parseInt(request.getParameter("no"));
        Tag tag = tagService.get(no);
        request.setAttribute("tag", tag);
        request.getRequestDispatcher("/tag/detail.jsp").include(request, response);

      } catch (Exception e) {
        request.setAttribute("exception", e);
        request.getRequestDispatcher("/error").forward(request, response);
        return;
      }
    }
  }


