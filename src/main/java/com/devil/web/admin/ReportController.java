package com.devil.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.service.ArticleService;
import com.devil.service.CommentService;
import com.devil.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
  @Autowired ArticleService articleService;
  @Autowired ReportService reportService;
  @Autowired CommentService commentService;

  @RequestMapping("list")
  public ModelAndView list(String keyword) throws Exception {

    ModelAndView mv = new ModelAndView();
    mv.addObject("reportList", reportService.list((String)null));
    mv.setViewName("/adminJsp/report/list.jsp");

    return mv;
  }
}
