package com.devil.web.app;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.service.BadgeService;

@Controller
@RequestMapping("/badge")
public class BadgeController {

  @Autowired ServletContext servletContext;
  @Autowired BadgeService badgeService;

  @RequestMapping("list")
  public ModelAndView list() throws Exception {
    ModelAndView mv = new ModelAndView();

    mv.addObject("list", badgeService.list((String)null));
    mv.setViewName("/appJsp/badge/list.jsp");
    return mv;

  }
}
