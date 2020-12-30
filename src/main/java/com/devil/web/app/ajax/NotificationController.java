package com.devil.web.app.ajax;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.service.NotificationService;
import com.devil.service.UserService;

@Controller("ajax.notificationController")
@RequestMapping("/ajax/notification")
public class NotificationController {
  @Autowired
  NotificationService notificationService;
  @Autowired
  UserService userService;

  @GetMapping("/updateReadDate")
  public String updateReadDate(int no, HttpServletRequest request) throws Exception {
    notificationService.updateReadDate(no);
    return "redirect:" + request.getHeader("Referer");
  }

}
