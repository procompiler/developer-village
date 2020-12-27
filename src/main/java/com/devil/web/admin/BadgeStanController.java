package com.devil.web.admin;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.BadgeStan;
import com.devil.service.BadgeStanService;

@Controller
@RequestMapping("/badgeStan")
public class BadgeStanController {

  @Autowired
  ServletContext servletContext;
  @Autowired
  BadgeStanService badgeStanService;

  @RequestMapping("add")
  public String add(BadgeStan badgeStan) throws Exception {
    badgeStanService.add(badgeStan);
    return "redirect:../badge/" + badgeStan.getNo();
  }

  @RequestMapping("update")
  public String update(BadgeStan badgeStan) throws Exception {
    badgeStanService.update(badgeStan);
    return "redirect:../badge/" + badgeStan.getNo();
  }
}
