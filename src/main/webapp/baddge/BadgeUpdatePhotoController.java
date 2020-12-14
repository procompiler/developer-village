package com.devil.web;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Badge;
import com.devil.service.BadgeService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class BadgeUpdatePhotoController  {
/*
  BadgeService badgeService;

  public BadgeUpdatePhotoController(BadgeService badgeService) {
    this.badgeService = badgeService;
  }

  @RequestMapping("/badge/updatePhoto")
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Badge badge = new Badge();
    badge.setNo(Integer.parseInt(request.getParameter("no")));

    // 태그 사진 파일 저장
    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String saveFilePath = request.getServletContext().getRealPath("/upload/badge" + filename);
      photoPart.write(saveFilePath);
      badge.setPhoto(filename);

      // 태그 사진의 썸네일 이미지 파일 생성하기
      generatePhotoThumbnail(saveFilePath);
    }

    if (badge.getPhoto() == null) {
      throw new Exception("사진을 선택하지 않았습니다.");
    }

    badgeService.update(badge);
    return "redirect:detail?no=" + badge.getNo();
  }

  private void generatePhotoThumbnail(String saveFilePath) {
    try {
      Thumbnails.of(saveFilePath)//
      .size(20, 20)//
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
*/
}
