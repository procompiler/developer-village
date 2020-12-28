package com.devil.web.app.ajax;

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
  public void updateReadDate(int no) throws Exception {
    notificationService.updateReadDate(no);
  }

}
