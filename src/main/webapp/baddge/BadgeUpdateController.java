package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Badge;
import com.devil.service.BadgeService;

@Controller
public class BadgeUpdateController  {
/*
  BadgeService badgeService;

  public BadgeUpdateController(BadgeService badgeService) {
    this.badgeService = badgeService;
  }

  @RequestMapping("/badge/update")
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Badge badge = new Badge();
    badge.setNo(Integer.parseInt(request.getParameter("no")));
    badge.setName(request.getParameter("name"));
    badge.setContent(request.getParameter("content"));

    badgeService.update(badge);
    return "redirect:list";
 
 }
*/
}
