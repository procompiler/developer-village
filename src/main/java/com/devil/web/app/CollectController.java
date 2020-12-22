package com.devil.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.devil.domain.User;
import com.devil.service.BadgeService;

@Controller
@RequestMapping("/collect")
@SessionAttributes("loginUser")
public class CollectController {
  @Autowired
  BadgeService badgeService;

  @RequestMapping("list")
  public void list(@ModelAttribute("loginUser") User loginUser, Model model) throws Exception {
    model.addAttribute("badgeList", badgeService.list(loginUser));
  }
}
