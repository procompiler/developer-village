package com.devil.web;
/*
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.service.BadgeService;

@Controller
public class BadgeDeleteController {

  BadgeService badgeService;

  public BadgeDeleteController(BadgeService badgeService) {
    this.badgeService = badgeService;
  }

  @RequestMapping("/badge/delete")
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {



    int no = Integer.parseInt(request.getParameter("no"));
    if (badgeService.delete(no) == 0) {
      throw new Exception("<p>해당 번호의 뱃지가 없습니다.</p>");
    }
    return "redirect:list";
  }
}
*/
