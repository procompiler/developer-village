package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.service.ReportService;

@WebServlet("/report/admitreport")
public class ReportAdmitServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ReportService reportService =
        (ReportService) ctx.getAttribute("reportService");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      if (reportService.admit(no) == 0) {
        throw new Exception("<p>해당 번호의 신고내역은 없습니다.</p>");
      }

      response.sendRedirect("../report/list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }
}
