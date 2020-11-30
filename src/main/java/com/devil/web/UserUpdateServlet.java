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
public class UserUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    // Servlet container에 들어 있는 UserService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    //out.println("<meta http-equiv='Refresh' content='1;url=list'>");
    out.println("<title>회원정보 수정</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>회원정보 수정</h1>");

      User user = new User();
      user.setNo(Integer.parseInt(request.getParameter("no")));
      user.setNickname(request.getParameter("nickname"));
      user.setPassword(request.getParameter("password"));
      user.setHompageURL(request.getParameter("homepage"));
      user.setGithubURL(request.getParameter("github"));
      user.setInstarURL(request.getParameter("instar"));
      user.setTwitterURL(request.getParameter("twitter"));
      user.setTech(request.getParameter("tech"));

      user.setPhoto(request.getParameter("photo"));
      int count = userService.update(user);

      if (count == 0) {
        out.println("<p>해당 번호의 회원이 없습니다.</p>");
      } else {
        out.println("<p>회원정보를 수정하였습니다.</p>");
      }

    } catch (Exception e) {
      out.println("<h2>작업 처리 중 오류 발생!</h2>");
      out.printf("<pre>%s</pre>\n", e.getMessage());

      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));
      out.println("<h3>상세 오류 내용</h3>");
      out.printf("<pre>%s</pre>\n", errOut.toString());
    }

    out.println("</body>");
    out.println("</html>");
  }

}
