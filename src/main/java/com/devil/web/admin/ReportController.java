package com.devil.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.service.ArticleService;
import com.devil.service.CommentService;
import com.devil.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
  @Autowired ArticleService articleService;
  @Autowired ReportService reportService;
  @Autowired CommentService commentService;

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("reportList", reportService.list((String)null));
  }
}
