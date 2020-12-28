package com.devil.web.app;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.service.BadgeStanService;

@Controller
@RequestMapping("/badgeStan")
public class BadgeStanController {

  @Autowired ServletContext servletContext;
  @Autowired BadgeStanService badgeStanService;

  @RequestMapping("list")
  public ModelAndView list(int badgeNo) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("list", badgeStanService.list(badgeNo));
    mv.setViewName("/appJsp/badgeStan/list.jsp");
    return mv;
  }
}
