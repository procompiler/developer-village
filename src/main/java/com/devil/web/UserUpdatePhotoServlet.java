package com.devil.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.devil.domain.User;
import com.devil.service.UserService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/user/updatePhoto")
public class UserUpdatePhotoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService =
        (UserService) ctx.getAttribute("userService");

    User user = new User();
    user.setNo(Integer.parseInt(request.getParameter("no")));

    // 회원 사진 파일 저장
    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String saveFilePath = ctx.getRealPath("/upload/user/" + filename);
      photoPart.write(saveFilePath);
      user.setPhoto(filename);

      // 회원 사진의 썸네일 이미지 파일 생성하기
      generatePhotoThumbnail(saveFilePath);
    }

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.printf("<meta http-equiv='Refresh' content='1;url=detail?no=%d'>",
        user.getNo());
    out.println("<title>회원사진수정</title></head>");
    out.println("<body>");

    try {
      out.println("<h1>회원 사진 수정</h1>");

      if (user.getPhoto() != null) {
        userService.update(user);
        out.println("<p>회원 사진을 수정하였습니다.</p>");
      } else {
        out.println("<p>사진을 선택하지 않았습니다.</p>");
      }

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
    out.println("</body>");
    out.println("</html>");
  }

  private void generatePhotoThumbnail(String saveFilePath) {
    try {
      Thumbnails.of(saveFilePath)//
      .size(40, 40)//
      .crop(Positions.CENTER)
      .outputFormat("jpg")//
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(saveFilePath)//
      .size(60, 60)//
      .crop(Positions.CENTER)
      .outputFormat("jpg")//
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(saveFilePath)//
      .size(80, 80)//
      .outputFormat("jpg") //
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(saveFilePath)
      .size(160, 160)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_160x160";
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
