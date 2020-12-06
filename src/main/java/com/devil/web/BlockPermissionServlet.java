package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.service.ReportService;

@WebServlet("/block/block-permission")
public class BlockPermissionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ReportService reportService = (ReportService) ctx.getAttribute("reportService");

    response.setContentType("text/html;charset=UTF-8");

    try {
      int no = Integer.parseInt(request.getParameter("reportNo"));
      request.setAttribute("report", reportService.get(no));
      request.getRequestDispatcher("/block/block-permission.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ServletContext ctx = request.getServletContext();

    try {
      //response.sendRedirect("../report/list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
