package com.devil.web.app;

import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Notification;
import com.devil.domain.User;
import com.devil.service.NotificationService;

@Controller
@RequestMapping("/notification")
public class NotificationController {

  @Autowired
  ServletContext servletContext;

  @Autowired
  NotificationService notificationService;

  @GetMapping("/list")
  public void list(HttpSession httpSession, Model model) throws Exception {
    User loginUser = (User) httpSession.getAttribute("loginUser");
    List<Notification> notificationList = notificationService.list(loginUser);
    for (Notification n : notificationList) {
      n.setDifTime(formatTimeString(n.getCreatedDate()));
    }
    model.addAttribute("notificationList", notificationList);
  }



  private static class MAX_TIME {
    public static final int SEC = 60;
    public static final int MIN = 60;
    public static final int HOUR = 24;
    public static final int DAY = 30;
    public static final int MONTH = 12;
  }

  public static String formatTimeString(Date createdDate) {
    long difTime = (System.currentTimeMillis() - createdDate.getTime()) / 1000;
    String msg = null;
    if (difTime < MAX_TIME.SEC) {
      msg = "방금 전";
    } else if ((difTime /= MAX_TIME.SEC) < MAX_TIME.MIN) {
      msg = difTime + "분 전";
    } else if ((difTime /= MAX_TIME.MIN) < MAX_TIME.HOUR) {
      msg = difTime + "시간 전";
    } else if ((difTime /= MAX_TIME.HOUR) < MAX_TIME.DAY) {
      msg = difTime + "일 전";
    } else if ((difTime /= MAX_TIME.DAY) < MAX_TIME.MONTH) {
      msg = difTime + "달 전";
    }
    return msg;
  }
}
