package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.devil.domain.User;
import com.devil.service.UserService;

@MultipartConfig(maxFileSize = 1024 * 1024 *10)
@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    User user = new User();
    request.setCharacterEncoding("UTF-8");
    user.setEmail(request.getParameter("email"));
    user.setNickname(request.getParameter("nickname"));
    user.setName(request.getParameter("name"));
    user.setPassword(request.getParameter("password"));
    user.setLoginType(request.getParameter("loginType"));

    // <input type="file"...> 입력 값 꺼내기
    Part photoPart = request.getPart("photo");

    // 회원 사진을 저장할 위치를 알아낸다.
    //  => 컨텍스트 루트/upload/파일
    //  => 파일을 저장할 때 사용할 파일명을 준비한다.
    String filename = UUID.randomUUID().toString();
    String saveFilePath = ctx.getRealPath(
        "/upload/" + filename);

    // 해당 위치에 업로드된 사진 파일을 저장한다.
    photoPart.write(saveFilePath);

    // DB에 사진 파일 이름을 저장하기 위해 객체에 보관한다.
    user.setPhoto(filename);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='2;url=list'>");
    out.println("<title>회원가입</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>[회원가입]</h1>");

      userService.add(user);

      out.println("<p>회원가입을 완료했습니다!</p>");
      out.println("<p>Devil개발자 커뮤니티에 어서오세요!</p>");

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