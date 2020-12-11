package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.service.ArticleService;
import com.devil.service.TagService;

@Controller
@RequestMapping("/article")
public class ArticleAddFormController {

  @Autowired ArticleService articleService;
  @Autowired TagService tagService;

  @RequestMapping("/form")
  protected ModelAndView form(HttpServletRequest request) throws Exception {

    ModelAndView mv = new ModelAndView();
    mv.addObject("list", tagService.list((String) null));
    mv.setViewName("/article/form.jsp");

    return mv;
  }
}
