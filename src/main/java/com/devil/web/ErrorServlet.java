package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>서버 오류</title></head>");
    out.println("<body>");
    out.println("<h1>서버 오류</h1>");
    Exception e = (Exception) request.getAttribute("exception");

    out.printf("<pre>%s</pre>\n", e.getMessage());

    out.println("<h3>상세 오류 내용</h3>");
    StringWriter errOut = new StringWriter();
    e.printStackTrace(new PrintWriter(errOut));
    out.printf("<pre>%s</pre>\n", errOut.toString());
    out.println("</body>");
    out.println("</html>");

  }
}
