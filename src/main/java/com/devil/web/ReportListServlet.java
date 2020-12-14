package com.devil.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Report;
import com.devil.service.ReportService;

@Controller
public class ReportListServlet {

  ReportService reportService;

  public ReportListServlet(ReportService reportService) {
    this.reportService = reportService;
  }

  @RequestMapping("/report/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");

    List<Report> reportArticleList = reportService.articleList((String)null);
    List<Report> reportCommentList = reportService.commentList((String)null);

    request.setAttribute("reportCommentList", reportCommentList);
    request.setAttribute("reportArticleList", reportArticleList);
    return "/report/list.jsp";

  }

}
