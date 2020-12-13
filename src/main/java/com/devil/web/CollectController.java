package com.devil.web;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.User;
import com.devil.service.BadgeService;

@Controller
@RequestMapping("/collect")
public class CollectController {
  @Autowired
  BadgeService badgeService;

  @RequestMapping("list")
  public ModelAndView list(HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("badgeList", badgeService.list((User)session.getAttribute("loginUser")));
    mv.setViewName("/collect/list.jsp");
    return mv;
  }

}
