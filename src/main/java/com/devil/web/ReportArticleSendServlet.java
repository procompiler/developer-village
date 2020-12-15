package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import com.devil.domain.Article;
import com.devil.domain.Report;
import com.devil.domain.User;
import com.devil.service.ReportService;

@Controller
public class ReportArticleSendServlet {

  ReportService reportService;

  public ReportArticleSendServlet(ReportService reportService) {
    this.reportService = reportService;
  }

  //@RequestMapping("/report/reportArticle-send")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Article reportedArticle = new Article();
    reportedArticle.setNo(Integer.parseInt(request.getParameter("articleNo")));

    User reporter = (User) request.getSession().getAttribute("loginUser");


    Report report = new Report();
    report.setReportedArticle(reportedArticle);
    report.setReporter(reporter);
    report.setReportTypeNo(Integer.parseInt(request.getParameter("reason")));

    response.setContentType("text/html;charset=UTF-8");

    reportService.reportArticle(report);
    return "../article/detail?no=" + reportedArticle.getNo();

  }
}
