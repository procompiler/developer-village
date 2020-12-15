package com.devil.web;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Article;
import com.devil.domain.Report;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
  @Autowired ArticleService articleService;
  @Autowired ReportService reportService;

  @RequestMapping("reportArticle")
  public ModelAndView reportArticle(int no) throws Exception {

    ModelAndView mv = new ModelAndView();
    mv.addObject("reportedArticle", articleService.get(no));
    mv.setViewName("/report/report-article.jsp");

    return mv;
  }

  @RequestMapping("reportArticle-send")
  public String reportArticleSend(Report report, int articleNo, String reason, HttpSession session) throws Exception {

    Article reportedArticle = new Article();
    reportedArticle.setNo(articleNo);

    User reporter = (User) session.getAttribute("loginUser");

    report.setReportedArticle(reportedArticle);
    report.setReporter(reporter);
    report.setReportTypeNo(Integer.parseInt(reason));

    reportService.reportArticle(report);
    return "../article/detail?no=" + reportedArticle.getNo();

  }
}
