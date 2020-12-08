package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Bookmark;
import com.devil.domain.User;
import com.devil.service.BookmarkService;

@WebServlet("/bookmark/delete")
public class BookmarkDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    BookmarkService bookmarkService = (BookmarkService)ctx.getAttribute("bookmarkService");
    Bookmark bookmark = new Bookmark();
    bookmark.setArticleNo(Integer.parseInt(request.getParameter("articleNo")));
    User loginUser = (User)request.getSession().getAttribute("loginUser");
    bookmark.setUserNo(loginUser.getNo());

    try {
      if (bookmarkService.delete(bookmark) == 0) {
        throw new Exception("북마크되어있지 않은 게시글입니다.");
      }
      response.sendRedirect(request.getHeader("Referer"));

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
