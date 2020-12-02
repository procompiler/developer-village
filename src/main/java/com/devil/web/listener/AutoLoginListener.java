package com.devil.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.devil.domain.User;
import com.devil.service.UserService;

@WebListener
public class AutoLoginListener implements HttpSessionListener{

  // 그 클라이언트에 대해서 세션이 만들어 질때 딱 한번 호출되서 자동 로그인 된다.
  @Override
  public void sessionCreated(HttpSessionEvent se) {
    System.out.println("로그인 세션생성됐어요");
    try {
      HttpSession session = se.getSession();
      UserService userService =
          (UserService) session.getServletContext().getAttribute("userService");
      User user = userService.get("abcd@gmail.com", "1111");
      session.setAttribute("loginUser", user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
