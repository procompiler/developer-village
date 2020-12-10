package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Block;
import com.devil.domain.Report;
import com.devil.service.BlockService;
import com.devil.service.ReportService;

@WebServlet("/block/add")
public class BlockAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ServletContext ctx = request.getServletContext();
    BlockService blockService = (BlockService) ctx.getAttribute("blockService");
    ReportService reportService = (ReportService) ctx.getAttribute("reportService");
    try {
      Block block = new Block();
      block.setBlockedDates(Integer.parseInt(request.getParameter("blockingDate")));
      block.setBlockedReason(request.getParameter("block-reason"));

      // report status set하기
      Report report = reportService.get(Integer.parseInt(request.getParameter("reportNo")));
      block.setReport(report);

      blockService.block(block);
      response.sendRedirect("../report/list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
