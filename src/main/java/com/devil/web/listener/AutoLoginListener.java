package com.devil.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.devil.domain.User;
import com.devil.service.UserService;

@WebListener
public class AutoLoginListener implements ServletRequestListener {
  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    try {
      HttpSession session = ((HttpServletRequest)sre.getServletRequest()).getSession();

      if (session.getAttribute("loginUser") == null) {
        UserService userService =
            (UserService) session.getServletContext().getAttribute("userService");
        User user = userService.get("abcd@test.com", "1111");
        session.setAttribute("loginUser", user);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
