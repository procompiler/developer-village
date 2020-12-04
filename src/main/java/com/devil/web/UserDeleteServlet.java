package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.service.UserService;

@WebServlet("/user/delete")
public class UserDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='1;url=list'>");
    out.println("<title>회원 탈퇴</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>회원 탈퇴</h1>");

      int no = Integer.parseInt(request.getParameter("no"));

      if (userService.delete(no) == 0) {
        out.println("<p>해당 번호의 유저가 없습니다.</p>");
      } else {
        out.println("<p>회원을 탈퇴하였습니다.</p>");
      }

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }

    out.println("</body>");
    out.println("</html>");
  }
}

