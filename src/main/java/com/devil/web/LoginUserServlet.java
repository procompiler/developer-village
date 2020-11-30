package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.devil.domain.User;

@WebServlet("/auth/loginUser")
public class LoginUserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    // 클라이언트 전용 보관소(HttpSession)를 준비한다.
    HttpSession session = request.getSession();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head><title>로그인 사용자 정보</title></head>");
    out.println("<body>");

    out.println("<h1>[로그인 사용자 정보]</h1>");

    User user = (User) session.getAttribute("loginUser");
    if (user == null) {
      out.println("<p>로그인 하지 않았습니다!</p>");
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
    out.printf("<img src='../upload/%s' alt='[%1$s]' height='100px'>", user.getPhoto());
    out.printf("<p>개인 홈페이지: %s</p>", user.getHomepageURL());

    out.println("<a href='logout'>로그아웃</a>");

    out.println("</body></html>");
  }
}
