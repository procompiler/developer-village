package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 웹 브라우저가 쿠키로 이메일을 보냈으면 꺼낸다.
    String email = "";

    Cookie[] cookies = request.getCookies();
    if(cookies != null) {
      for(Cookie cookie : cookies) {
        if(cookie.getName().equals("email")) {
          email = cookie.getValue();
          break;
        }
      }
    }

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인</h1>");
    out.println("<form action='login' method='post'>");
    out.printf("이메일: <input type='email' name='email' value='%s'><br>\n", email);
    out.println("암호: <input type='password' name='password'><br>");
    out.println("<input type='checkbox' name='saveEmail'> 이메일 저장<br>");

    out.println("<button>로그인</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");

  }

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
      // 클라이언트가 보낸 데이터를 꺼낸다.
      String email = request.getParameter("email");
      String password = request.getParameter("password");


      // 클라이언트에게 보낼 email쿠키를 준비한다.
      Cookie emailCookie = new Cookie("email", email);

      if(request.getParameter("saveEmail") != null) {

        // 응답헤더에 email쿠키를 포함시킨다.
        response.addCookie(emailCookie);
        // => 이 쿠키는 로그인폼에서만 사용할 것이기 때문에
        //    사용범위를 현재 서블릿의 URL에 한정한다.
        // => 사용범위를 지정하지 않으면 자동으로 현재 URL에 한정된다.
        //    즉 사용범위를 지정할 필요가 없다.
        // => 대신 웹브라우저를 종료하거나 컴퓨터를 종료한 후에도 유지해야 하기 때문에
        //    유효기간을 설정해준다.
        emailCookie.setMaxAge(60 * 60 * 24 * 7);
      } else {
        emailCookie.setMaxAge(0); // 유효기간이 0이면 삭제하라는 의미다.
      }

      if (session.getAttribute("loginUser") != null) {
        out.println("<p>로그인 된 상태입니다.</p>");

      } else {

        // 서블릿이 로그인 작업에 사용할 도구를 준비한다.
        ServletContext ctx = request.getServletContext();
        UserService userService = (UserService) ctx.getAttribute("userService");

        User user = userService.get(email, password);
        if (user == null) {
          out.println("<p>사용자 정보가 맞지 않습니다.</p>");

        } else {
          // 로그인이 성공했으면 메인 화면으로 이동한다.
          session.setAttribute("loginUser", user);

          // => forward?
          //    - 로그인의 결과가 메인 화면인가?
          //    - 아님!! 그러니 이런 경우에는 forward가 적절하지 않다.
          //    - refresh나 redirect를 써야한다.
          //          request.getRequestDispatcher("/index.html").forward(request, response);

          // 실행 목적이 다를 때는 refresh나 redirect를 통해 새 요청을 하도록 만들어야 한다.

          response.sendRedirect("../index.html");
          return;
        }
      }
      response.addCookie(emailCookie);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }

    out.println("</body>");
    out.println("</html>");

    response.setHeader("Refresh", "1;url=../index.html");
  }
}
