package com.devil.web;

import java.io.IOException;
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
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
@MultipartConfig(maxFileSize = 1024 * 1024 *10)
@WebServlet("/badge/add")
public class BadgeAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private String uploadDir;

  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath("/upload/badge/");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    BadgeService badgeService = (BadgeService) ctx.getAttribute("badgeService");


    Badge badge = new Badge();
    badge.setName(request.getParameter("name"));
    badge.setContent(request.getParameter("content"));
    Part photoPart = request.getPart("photo");

    String filename = "";
    if (photoPart.getSize() > 0) {
      // 파일을 선택해서 업로드 했다면,
      filename = UUID.randomUUID().toString();
      photoPart.write(this.uploadDir + "/" + filename);
    }

    Thumbnails.of(this.uploadDir + "/" + filename)//
    .size(20, 20)//
    .outputFormat("jpg")//
    .toFiles(new Rename() {
      @Override
      public String apply(String name, ThumbnailParameter param) {
        return name + "_20x20";
      }
    });

    // 회원 사진을 저장할 위치를 알아낸다.
    //  => 컨텍스트 루트/upload/파일
    //  => 파일을 저장할 때 사용할 파일명을 준비한다.
    //String filename = UUID.randomUUID().toString();
    //    String saveFilePath = ctx.getRealPath(
    //        "/upload/badge/" + filename);
    //    // 해당 위치에 업로드된 사진 파일을 저장한다.
    //    photoPart.write(saveFilePath);
    //    // DB에 사진 파일 이름을 저장하기 위해 객체에 보관한다.
    badge.setPhoto(filename);
    // 웹브라우저 제목에 출력될 내용

    try {
      request.setCharacterEncoding("UTF-8");
      badge.setName(request.getParameter("name"));
      badge.setContent(request.getParameter("content"));
      badgeService.add(badge);

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}