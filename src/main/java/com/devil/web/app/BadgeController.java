package com.devil.web.app;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.service.BadgeService;

@Controller
@RequestMapping("/badge")
public class BadgeController {

  @Autowired ServletContext servletContext;
  @Autowired BadgeService badgeService;

  @GetMapping("list")
  public String list(Model model) throws Exception {
    model.addAttribute("list", badgeService.list(""));
    return "badge/list";

  }
}
