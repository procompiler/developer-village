package com.devil.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.BadgeStan;
import com.devil.service.BadgeStanService;

@Controller
@RequestMapping("/badgeStan")
public class BadgeStanController {

  @Autowired
  BadgeStanService badgeStanService;

  @PostMapping("add")
  public String add(BadgeStan badgeStan) throws Exception {
    badgeStanService.add(badgeStan);
    return "redirect:../badge/" + badgeStan.getBadgeNo();
  }

  @PostMapping("update")
  public String update(BadgeStan badgeStan, int stanNo) throws Exception {
    badgeStanService.update(badgeStan.setNo(stanNo));
    return "redirect:../badge/" + badgeStan.getBadgeNo();
  }
  
  @GetMapping("delete")
  public String delete(BadgeStan badgeStan) throws Exception {
    badgeStanService.delete(badgeStan.getNo());
    return "redirect:../badge/" + badgeStan.getBadgeNo();
  }
}
