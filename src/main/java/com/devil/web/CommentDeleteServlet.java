package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.service.CommentService;

@WebServlet("/comment/delete")
public class CommentDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    CommentService commentService = (CommentService) ctx.getAttribute("commentService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>댓글 삭제</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>댓글 삭제</h1>");

      int no = Integer.parseInt(request.getParameter("no"));

      if (commentService.delete(no) == 0) {
        out.println("<p>해당 댓글이 없습니다.</p>");
      } else {
        out.println("<p>댓글을 삭제하였습니다.</p>");
      }
      response.setHeader("Refresh", "1;url=../article/detail?no=" + request.getParameter("articleNo"));

    } catch (Exception e) {
      out.println("<h2>작업 처리 중 오류 발생!</h2>");
      out.printf("<pre>%s</pre>\n",e.getMessage());

      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));
      out.println("<h3>상세 오류 내용</h3>");
      out.printf("<pre>%s</pre>\n", errOut.toString());
    }

    out.println("</body>");
    out.println("</html>");
  }
}

