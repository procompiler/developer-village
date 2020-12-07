package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.devil.domain.Article;
import com.devil.domain.Report;
import com.devil.domain.User;
import com.devil.service.ReportService;

@WebServlet("/report/reportArticle-send")
public class ReportArticleSendServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession();
    ServletContext ctx = request.getServletContext();
    ReportService reportService =
        (ReportService) ctx.getAttribute("reportService");


    Article reportedArticle = new Article();
    reportedArticle.setNo(Integer.parseInt(request.getParameter("articleNo")));

    User reporter = (User) session.getAttribute("loginUser");


    Report report = new Report();
    report.setReportedArticle(reportedArticle);
    report.setReporter(reporter);
    report.setReportTypeNo(Integer.parseInt(request.getParameter("reason")));

    response.setContentType("text/html;charset=UTF-8");

    try {
      reportService.report(report);
      response.sendRedirect("../article/detail?no=" + reportedArticle.getNo());

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
