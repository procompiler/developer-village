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
import com.devil.domain.User;
import com.devil.service.UserService;

@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");


    User user = new User();
    request.setCharacterEncoding("UTF-8");
    user.setEmail(request.getParameter("email"));
    user.setNickname(request.getParameter("nickname"));
    user.setName(request.getParameter("name"));
    user.setPassword(request.getParameter("password"));
    user.setLoginType(request.getParameter("loginType"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='2;url=list'>");
    out.println("<title>회원가입</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>[회원가입]</h1>");

      userService.add(user);

      out.println("회원가입을 완료했습니다!");
      out.println("Devil개발자 커뮤니티에 어서오세요!");

    }catch (Exception e) {
      out.printf("<p>작업 처리 중 오류 발생! - %s</p>\n", e.getMessage());
      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));
      out.printf("<pre>%s</pre>\n", errOut.toString());
    }
    out.println("</body>");
    out.println("</html>");
  }
}