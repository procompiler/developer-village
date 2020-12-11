package com.devil.web;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Tag;
import com.devil.service.TagService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class TagAddController {
  TagService tagService;
  
  public TagAddController(TagService tagService) {
    this.tagService = tagService;
  }

  @RequestMapping("/tag/add")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Tag tag = new Tag();
    tag.setName(request.getParameter("name"));
    tag.setTagColor(request.getParameter("tagColor").split("#")[1]);
    tag.setFontColor(request.getParameter("fontColor").split("#")[1]);

    Part photoPart = request.getPart("photo");
    String filename = UUID.randomUUID().toString();
    String saveFilePath = request.getServletContext().getRealPath(
        "/upload/tag/" + filename);
    // 해당 위치에 업로드된 사진 파일을 저장한다.
    photoPart.write(saveFilePath);

    // DB에 사진 파일 이름을 저장하기 위해 객체에 보관한다.
    tag.setPhoto(filename);

    generatePhotoThumbnail(saveFilePath);
      tagService.add(tag);
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