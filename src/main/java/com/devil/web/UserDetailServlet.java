package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.User;
import com.devil.service.UserService;

@WebServlet("/user/detail")
public class UserDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Servlet container에 들어 있는 UserService를 꺼낸다.
    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");

    // 웹주소에 동봉된 데이터(Query String: qs)를 읽는다.
    // 클라이언트가 URL에 데이터를 포함해서 보낸다.
    // 숫자 데이터가 넘어오기 때문에 깨질 염려가 없다.
    int no = Integer.parseInt(request.getParameter("no"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head><title>회원 조회</title>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href='../style.css'></head>");
    out.println("<body>");

    try {
      out.println("<h1>[회원 상세조회]</h1>");

      User user = userService.get(no);

      if (user == null) {
        out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      out.printf("<p>번호: %s</p>", user.getNo());
      out.printf("<p>이메일: %s</p>", user.getEmail());
      out.printf("<p>닉네임: %s</p>", user.getNickname());
      out.printf("<p>이름: %s</p>", user.getName());

      DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
      out.printf("<p>가입일: %s</p>", format.format(user.getCreatedDate()));

      int loginType = Integer.parseInt(user.getLoginType());
      if(loginType == 1) {
        out.println("<p>로그인 유형: 기본 가입회원</p>");
      } else if (loginType == 2) {
        out.println("<p>로그인 유형: 구글 가입회원</p>");
      } else if (loginType == 3) {
        out.println("<p>로그인 유형: 깃허브 가입회원</p>");
      }

      out.printf("<p>기술 목록: %s</p>", user.getTech());
      out.printf("<p>사진: %s</p>", user.getPhoto());
      out.printf("<p>개인 홈페이지: %s</p>", user.getHompageURL());

      out.println("<a href='list' style='color:blue;'>목록으로</a>");
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


