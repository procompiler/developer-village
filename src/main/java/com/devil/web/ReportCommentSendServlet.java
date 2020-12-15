package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Comment;
import com.devil.domain.Report;
import com.devil.domain.User;
import com.devil.service.ReportService;

@Controller
public class ReportCommentSendServlet {

  ReportService reportService;

  public ReportCommentSendServlet(ReportService reportService) {
    this.reportService = reportService;
  }

  @RequestMapping("/report/reportComment-send")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");

    Comment reportedComment = new Comment();
    reportedComment.setNo(Integer.parseInt(request.getParameter("commentNo")));
    reportedComment.setArticleNo(Integer.parseInt(request.getParameter("commentArticleNo")));

    User reporter = (User) request.getSession().getAttribute("loginUser");

    Report report = new Report();
    report.setReportedComment(reportedComment);
    report.setReporter(reporter);
    report.setReportTypeNo(Integer.parseInt(request.getParameter("reason")));

    reportService.reportComment(report);
    return "../article/detail?no=" + reportedComment.getArticleNo();

  }
}
