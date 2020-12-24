package com.devil.web.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.devil.domain.Notification;
import com.devil.domain.User;
import com.devil.service.NotificationService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/notification")
public class NotificationController {

  @Autowired
  ServletContext servletContext;

  @Autowired
  NotificationService notificationService;
  @Autowired
  UserService userService;

  @GetMapping("/list")
  public void list(HttpSession httpSession, Model model) throws Exception {
    User loginUser = (User) httpSession.getAttribute("loginUser");
    System.out.println(loginUser.getNo());
    System.out.println(loginUser.getName());
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", loginUser.getNo());

    model.addAttribute("user", userService.get(params));
    List<Notification> notificationList = notificationService.list(loginUser);
    model.addAttribute("notificationList", notificationList);
  }
}
