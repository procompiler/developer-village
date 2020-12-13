package com.devil.web;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import com.devil.domain.Tag;
import com.devil.service.TagService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;


@Controller
public class TagUpdatePhotoController {
  TagService tagService;

public TagUpdatePhotoController(TagService tagService) {
  this.tagService = tagService;
}

  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      Tag tag = new Tag();
      tag.setNo(Integer.parseInt(request.getParameter("no")));

      // 태그 사진 파일 저장
      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        String saveFilePath = request.getServletContext().getRealPath("/upload/tag/" + filename);
        photoPart.write(saveFilePath);
        tag.setPhoto(filename);

        // 태그 사진의 썸네일 이미지 파일 생성하기
        generatePhotoThumbnail(saveFilePath);
      }
      int count = tagService.update(tag);

      if (count == 0) {
        throw new Exception("해당 태그가 없습니다.");
      }
      return "redirect:list";
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
