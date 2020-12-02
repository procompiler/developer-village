package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.devil.domain.User;
import com.devil.service.UserService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/user/followTag")
public class UserFollowTagServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService =
        (UserService) ctx.getAttribute("userService");

    HttpSession session = request.getSession();
    User loginUser = session.getAttribute("loginUser");
    User user = new User();
    user.setNo(c));
    user.setNo()

    
    if (loginUser != null) {
      .setWriter(loginUser);
      articleService.add(article);
      out.println("게시글을 등록했습니다.");
    } else {
      out.println("로그인을 해주세요!");
    }

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();


    try {

      if (user.getPhoto() != null) {
        userService.update(user);
        out.println("<p>회원 사진을 수정하였습니다.</p>");
      } else {
        out.println("<p>사진을 선택하지 않았습니다.</p>");
      }

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }
}
