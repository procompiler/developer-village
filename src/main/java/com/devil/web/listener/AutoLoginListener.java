package com.devil.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.devil.domain.User;

@WebListener
public class AutoLoginListener implements ServletRequestListener {

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    HttpSession session = ((HttpServletRequest)sre.getServletRequest()).getSession();
    if (session.getAttribute("loginUser") == null) {
      User user = new User().setNo(11).setName("배두나").setNickname("시간이nullnull").setEmail("abcd@gmail.com").setPhoto("user11");
      session.setAttribute("loginUser", user);
      System.out.println(user.getNickname());
    }
  }
}
