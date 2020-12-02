package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
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
    
    User loginUser = (User) request.getSession().getAttribute("loginUser");


    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head><title>태그목록</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
    out.println("<body>");

    try {
      out.println("<h1>태그 목록</h1>");
      out.println("<button type='button' onclick=\"location.href='form.html'\">태그 추가</button>");

      List<Tag> list = tagService.list(null);
      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr>" // table row
          + "<th>번호</th>" // table header
          + "<th>태그이름</th>" + "<th>태그사진</th>" + "<th>미리보기</th>" + "<th>삭제여부</th>" + "<th></th></tr>");
      out.println("</thead>");
      out.println("<tbody>");
      List<Integer> tagNoList = new ArrayList<>();
      for (Tag t : loginUser.getTags()) {
        tagNoList.add(t.getNo());
      }
      for (Tag tag : list) {
        boolean followed = tagNoList.contains(tag.getNo());
        out.printf(
            "<tr>"
        + "<td>%d</td>"
        + "<td id=\"title\"><a href='detail?no=%1$d'>%s</a></td>"
        + "<td><img src='../upload/tag/%s_80x80.png' alt='%3$s'></td>"
        + "<td><span id=\"color\" style=\"background-color:#%s; color:#%s\">%2$s</span></td>"
        + "<td>%s</td>"
        + "<td><button type='button' %s onclick=\"location.href='../user/%sfollowTag?uno=%1$d'\">%s</button></td>"
        + "</tr>\n",
            tag.getNo(),
            tag.getName(),
            tag.getPhoto(),
            tag.getTagColor(),
            tag.getFontColor(),
            tag.getState() == 1 ? "" : "삭제됨",
            followed ? "class='btn-hollow'" : "",
            followed ? "un" : "",
            followed ? "팔로우취소" : "팔로우");
      }
      
      out.println("</tbody>");
      out.println("</table>");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }

}
