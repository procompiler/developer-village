package com.devil.web.app.json;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.devil.domain.Notification;
import com.devil.domain.User;
import com.devil.service.NotificationService;
import com.devil.service.UserService;
import com.google.gson.Gson;

@Controller("json.notificationController")
@RequestMapping("/json/notification")
public class NotificationController {

  @Autowired
  NotificationService notificationService;
  @Autowired
  UserService userService;
  @GetMapping(value = "list", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String list(HttpSession httpSession, Model model) throws Exception {
    User loginUser = (User) httpSession.getAttribute("loginUser");
    System.out.println(loginUser.getNo());
    System.out.println(loginUser.getName());
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", loginUser.getNo());
    model.addAttribute("user", userService.get(params));
    
    List<Notification> notificationList = notificationService.list(loginUser);
    for (Notification n : notificationList) {
      System.out.println(n.getType());
      n.setDifTime(formatTimeString(n.getCreatedDate()));
    }
    return new Gson().toJson(notificationList);
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
