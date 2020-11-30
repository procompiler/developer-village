package com.devil.web;

import java.io.IOException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.devil.domain.Badge;
import com.devil.service.BadgeService;
@MultipartConfig(maxFileSize = 1024 * 1024 *10)
@WebServlet("/badge/add")
public class BadgeAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    BadgeService badgeService = (BadgeService) ctx.getAttribute("badgeService");
   
    request.setCharacterEncoding("UTF-8");

    Badge badge = new Badge();
    badge.setName(request.getParameter("name"));
    badge.setContent(request.getParameter("content"));
    
    Part photoPart = request.getPart("photo");

    // 회원 사진을 저장할 위치를 알아낸다.
    //  => 컨텍스트 루트/upload/파일
    //  => 파일을 저장할 때 사용할 파일명을 준비한다.
    String filename = UUID.randomUUID().toString();
    String saveFilePath = ctx.getRealPath(
        "/upload/" + filename);
    // 해당 위치에 업로드된 사진 파일을 저장한다.
    photoPart.write(saveFilePath);
    // DB에 사진 파일 이름을 저장하기 위해 객체에 보관한다.
    badge.setPhoto(filename);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    // 웹브라우저 제목에 출력될 내용
    out.println("<head>");
    out.println("<meta http-equiv='Refresh' content='2;url=list'>");
    out.println("<title>뱃지 등록</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>[뱃지 등록]</h1>");

      request.setCharacterEncoding("UTF-8");
      badge.setName(request.getParameter("name"));
      badge.setContent(request.getParameter("content"));
        badgeService.add(badge);
        out.println("뱃지를 등록했습니다.");

    }catch (Exception e) {
      out.printf("<p>작업 처리 중 오류 발생! - %s</p>\n", e.getMessage());
      StringWriter errOut = new StringWriter();
      e.printStackTrace(new PrintWriter(errOut));
      out.printf("<pre>%s</pre>\n", errOut.toString());
    }
    out.println("</body>");
    out.println("</html>");
  }
}