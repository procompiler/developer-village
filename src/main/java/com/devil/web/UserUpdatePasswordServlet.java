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

@WebServlet("/user/update")
public class UserUpdatePasswordServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Servlet container에 들어 있는 UserService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");

    User user = new User();
    user.setNo(Integer.parseInt(request.getParameter("no")));
    user.setNickname(request.getParameter("nickname"));
    user.setPassword(request.getParameter("password"));
    user.setHomepageURL(request.getParameter("homepage"));
    user.setGithubURL(request.getParameter("githubURL"));
    user.setInstarURL(request.getParameter("instarURL"));
    user.setTwitterURL(request.getParameter("twitterURL"));
    user.setTech(request.getParameter("tech"));
    user.setBio(request.getParameter("bio"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.printf("<meta http-equiv='Refresh' content='1;url=../user/detail?no=%d'>", user.getNo());
    out.println("<title>회원정보 수정</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>회원정보 수정</h1>");

      userService.update(user);

      out.println("<p>회원정보를 수정하였습니다.</p>");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }

    out.println("</body>");
    out.println("</html>");
  }

}
