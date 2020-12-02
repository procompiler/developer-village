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
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
    out.println("<body>");
    try {

      out.println("<h1><a href='list' style='text-decoration:none;'>유저 목록</a></h1>");
      out.println("<a href='form.html' style='color:green;'>회원 가입</a><br>");

      List<User> list = null;
      String keyword = request.getParameter("keyword");

      if(keyword != null) {
        list = userService.list(keyword);
      } else {
        list = userService.list(null);
      }

      out.println("<p>");
      out.println("<form action='list' method='get'>");
      out.printf("<input type='text' name='keyword' value='%s'>\n",
          keyword != null ? keyword : "");
      out.println("<button>유저 검색</button>");
      out.println("</form>");
      out.println("</p>");

      out.println("<table border='1'>");
      out.println("<tr>" // table row
          + "<th>번호</th>" // table header
          + "<th>이메일</th>"
          + "<th>닉네임</th>"
          + "<th>이름</th>"
          + "<th>가입일</th>"
          + "<th>상태</th>"
          + "<th>로그인타입</th>"
          + "<th>신고승인</th>"
          + "</tr>");

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      for (User user : list) {
        String loginType = null;
        switch (user.getLoginType()) {
          case "1":
            loginType = "기본";
            break;
          case "2":
            loginType = "구글";
            break;
          case "3":
            loginType = "깃허브";
            break;
        }

        out.printf("<tr>"
            + "<td>%d</td>"
            + "<td>%s</td>"
            + "<td><a href='detail?no=%d'><img src='../upload/user/%s_40x40.jpg' style='border-radius: 70px' alt='[%s_80x80]'>%s</a></td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td style='color:red;'>%s</td>"
            + "<td>%s</td>"
            + "</tr>\n",
            user.getNo(),
            user.getEmail(),
            user.getNo(),
            user.getPhoto(),
            user.getPhoto(),
            user.getNickname(),
            user.getName(),
            formatter.format(user.getCreatedDate()),
            user.getState() == 1 ? "" : "탈퇴한 회원",
                loginType);
      }
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
