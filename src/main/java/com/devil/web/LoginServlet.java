package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.devil.domain.User;
import com.devil.service.UserService;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 클라이언트 전용(session) 보관소를 준비한다.
    HttpSession session = request.getSession();

    // 클라이언트로 데이터를 출력할 때 사용할 스트림 준비
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='2;url=../index.html'>");
    out.println("<title>로그인</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>로그인</h1>");

      String email = request.getParameter("email");
      String password = request.getParameter("password");

      ServletContext ctx = request.getServletContext();
      UserService userService = (UserService) ctx.getAttribute("userService");

      User user = userService.get(email, password);

      session.setAttribute("loginUser", user);
      out.printf("<p>%s 님 반갑습니다.</p>\n", user.getNickname());

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }

    out.println("</body>");
    out.println("</html>");
  }
}
