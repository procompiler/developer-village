package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Badge;
import com.devil.service.BadgeService;
/*
@Controller
public class BadgeDetailController {

  BadgeService badgeService;

  public BadgeDetailController(BadgeService badgeService) {
    this.badgeService = badgeService;
  }

  @RequestMapping("/badge/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    int no = Integer.parseInt(request.getParameter("no"));

    Badge badge = badgeService.get(no);
    if (badge == null) {
      throw new Exception("해당 뱃지가 없습니다!");
    }

    request.setAttribute("badge", badge);
    return "/badge/detail.jsp";
  }
}
*/
