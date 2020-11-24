package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.UserService;

@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {
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
    out.println("<head><title>유저 목록</title></head>");
    out.println("<style> table { border-top: 1px solid #444444; border-collapse: collapse;}" +
        "  th, td { border-bottom: 1px solid #444444; padding: 10px;} </style>");
    out.println("<body>");
    try {
      out.println("<h1>유저 목록</h1>");

      List<User> list = userService.list(null);
      out.println("<table border='1'>");
      out.println("<tr>" // table row
          + "<th>번호</th>" // table header
          + "<th>이메일</th>" + "<th>닉네임</th>" + "<th>이름</th>" + "<th>가입일</th>" + "<th>로그인타입</th>" + "</tr>");

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      for (User user : list) {
        out.printf(
            "<tr>" + "<td>%d</td>" + "<td>%s</td>"+ "<td>%s</td>" + "<td>%s</td>" + "<td>%s</td>" + "<td>%s</td>"
                + "</tr>\n",
                user.getNo(), user.getEmail(), user.getNickname(),
                user.getName(), formatter.format(user.getCreatedDate()), user.getLoginType());
      }
      out.println("</table>");

    } catch (Exception e) {
      out.printf("<p>작업 처리 중 오류 발생! - %s</p>\n", e.getMessage());

      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));

      out.printf("<pre>%s</pre>\n", errOut.toString());
    }
    out.println("</body>");
    out.println("</html>");
  }

}
