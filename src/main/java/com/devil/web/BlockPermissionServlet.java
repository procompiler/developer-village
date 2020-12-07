package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.devil.domain.Block;
import com.devil.domain.User;
import com.devil.service.BlockService;
import com.devil.service.ReportService;
import com.devil.service.UserService;

@WebServlet("/block/block-permission")
public class BlockPermissionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    ReportService reportService = (ReportService) ctx.getAttribute("reportService");
    UserService userService = (UserService) ctx.getAttribute("userService");
    response.setContentType("text/html;charset=UTF-8");

    try {
      int no = Integer.parseInt(request.getParameter("reportNo"));
      request.setAttribute("report", reportService.get(no));

      User reportedUser = userService.get(
          Integer.parseInt(request.getParameter("reportedUser")));
      request.setAttribute("reportedUser", reportedUser);

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
    BlockService blockService = (BlockService) ctx.getAttribute("blockService");
    UserService userService = (UserService) ctx.getAttribute("userService");

    try {
      Block block = new Block();
      block.setBlockedDates(Integer.parseInt(request.getParameter("blockingDate")));
      block.setBlockedReason(request.getParameter("block-reason"));

      User reportedUser = userService.get(
          Integer.parseInt(request.getParameter("reportedUser")));
      block.setReportedUser(reportedUser);

      // report status set하기

      blockService.block(block);
      response.sendRedirect("../report/list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
