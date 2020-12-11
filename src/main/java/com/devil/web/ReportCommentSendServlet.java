package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.devil.domain.Comment;
import com.devil.domain.Report;
import com.devil.domain.User;
import com.devil.service.ReportService;

@WebServlet("/report/reportComment-send")
public class ReportCommentSendServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession();
    ServletContext ctx = request.getServletContext();
    ReportService reportService =
        (ReportService) ctx.getAttribute("reportService");


    Comment reportedComment = new Comment();
    reportedComment.setNo(Integer.parseInt(request.getParameter("commentNo")));
    reportedComment.setArticleNo(Integer.parseInt(request.getParameter("commentArticleNo")));

    User reporter = (User) session.getAttribute("loginUser");

    Report report = new Report();
    report.setReportedComment(reportedComment);
    report.setReporter(reporter);
    report.setReportTypeNo(Integer.parseInt(request.getParameter("reason")));

    response.setContentType("text/html;charset=UTF-8");

    try {
      reportService.reportComment(report);
      response.sendRedirect("../article/detail?no=" + reportedComment.getArticleNo());

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
