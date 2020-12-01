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

      out.println("<form action='updatePhoto' method='post' enctype='multipart/form-data'>");
      out.printf("<input type='hidden' name='no' value='%d'><br>\n",
          user.getNo());
      out.printf("<img src='../upload/user/%s_80x80.jpg' alt='[%1$s]'>", user.getPhoto());
      out.println("<input type='file' name='photo'><br>");
      out.println("<button>변경</button>");
      out.println("</form>");
      out.println("<br>");

      out.println("<form action='update' method='post'>");
      out.printf("<p>회원번호: <input type='text' name='no' value='%d' readonly style='display:hidden;'></p>", user.getNo());
      out.printf("<p>닉네임: <input type='text' name='nickname' value='%s'></p>", user.getNickname());

      out.printf("<p>이메일: %s</p>", user.getEmail());
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

      out.printf("<p>기술: <input type='text' name='tech' value='%s'></p>", user.getTech());
      out.printf("<p>개인 홈페이지: <input type='text' name='homepage' value='%s'></p>", user.getHomepageURL());
      out.printf("<p>깃허브: <input type='text' name='githubURL' value='%s'></p>", user.getGithubURL());
      out.printf("<p>인스타그램: <input type='text' name='instarURL' value='%s'></p>", user.getInstarURL());
      out.printf("<p>트위터: <input type='text' name='twitterURL' value='%s'></p>", user.getTwitterURL());

      out.println("<button>정보 수정</button>");
      out.printf("<button type='button' class='btn-danger' onclick=\"location.href='delete?no=%d'\">회원 삭제</button>", user.getNo());

      out.println("<a href='list' style='color:blue;'>회원 목록으로</a>");
      out.println("</form>");
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


