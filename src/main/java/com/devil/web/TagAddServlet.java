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
import com.devil.domain.Tag;
import com.devil.service.TagService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 *10)
@WebServlet("/tag/add")
public class TagAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext ctx = request.getServletContext();
    TagService tagService = (TagService) ctx.getAttribute("tagService");

    request.setCharacterEncoding("UTF-8");

    Tag tag = new Tag();
    tag.setName(request.getParameter("name"));
    tag.setTagColor(request.getParameter("tagColor").split("#")[1]);
    tag.setFontColor(request.getParameter("fontColor").split("#")[1]);
    
    Part photoPart = request.getPart("photo");
    String filename = UUID.randomUUID().toString();
    String saveFilePath = ctx.getRealPath(
        "/upload/tag/" + filename);
    // 해당 위치에 업로드된 사진 파일을 저장한다.
    photoPart.write(saveFilePath);
    
    // DB에 사진 파일 이름을 저장하기 위해 객체에 보관한다.
    tag.setPhoto(filename);
    
    generatePhotoThumbnail(saveFilePath);

    response.setContentType("text/html;charset=UTF-8");

    try {
      tagService.add(tag);
      response.sendRedirect("list");
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error").forward(request, response);
      return;
    }
  }
  
  private void generatePhotoThumbnail(String saveFilePath) {
    try {
      Thumbnails.of(saveFilePath)//
      .size(40, 40)//
      .crop(Positions.CENTER)
      .outputFormat("png")//
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(saveFilePath)//
      .size(80, 80)//
      .outputFormat("png") //
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(saveFilePath)
      .size(160, 160)
      .outputFormat("png")
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