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
import com.devil.domain.User;
import com.devil.service.UserService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 *10)
@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    UserService userService = (UserService) ctx.getAttribute("userService");

    response.setContentType("text/html;charset=UTF-8");

    User user = new User();
    user.setEmail(request.getParameter("email"));
    user.setNickname(request.getParameter("nickname"));
    user.setName(request.getParameter("name"));
    user.setPassword(request.getParameter("password"));
    user.setLoginType(request.getParameter("loginType"));

    // <input type="file"...> 입력 값 꺼내기
    Part photoPart = request.getPart("photo");

    // 회원 사진을 저장할 위치를 알아낸다.
    //  => 컨텍스트 루트/upload/파일
    //  => 파일을 저장할 때 사용할 파일명을 준비한다.

    String filename = UUID.randomUUID().toString();
    String saveFilePath = ctx.getRealPath("/upload/user/" + filename);

    // 해당 위치에 업로드된 사진 파일을 저장한다.
    photoPart.write(saveFilePath);

    // DB에 사진 파일 이름을 저장하기 위해 객체에 보관한다.
    user.setPhoto(filename);

    // 회원 사진의 썸네일 생성하기
    generatePhotoThumnail(saveFilePath);

    try {

      userService.add(user);
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }

  private void generatePhotoThumnail(String saveFilePath) {
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
      .size(80, 80)//
      .outputFormat("jpg") //
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(saveFilePath)//
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