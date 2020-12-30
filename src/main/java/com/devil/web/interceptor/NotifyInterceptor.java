package com.devil.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import com.devil.domain.User;
import com.devil.service.NotificationService;
import com.devil.service.UserService;

public class NotifyInterceptor implements HandlerInterceptor {

  NotificationService notificationService;

  public NotifyInterceptor(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("알림 가져오기");
    User loginUser = (User) request.getSession().getAttribute("loginUser");
    if (request.getSession().getAttribute("loginUser") != null) {
      loginUser.setNotiCount(notificationService.size(loginUser));
    }
    return true;
  }
}
