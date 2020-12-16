package com.devil.web;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Badge;
import com.devil.service.BadgeService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("/badge")
public class BadgeController {

  @Autowired ServletContext servletContext;
  @Autowired BadgeService badgeService;

  @RequestMapping("/form")
  public ModelAndView form() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/badge/form.jsp");

    return mv;
  }

  @RequestMapping("/add")
  public String add(String name, String content,Part photoFile) throws Exception {

    String filename = UUID.randomUUID().toString();
    String saveFilePath = servletContext.getRealPath("/upload/badge/" + filename);

    Badge badge = new Badge();
    badge.setName(name);
    badge.setContent(content);

    photoFile.write(saveFilePath);
    badge.setPhoto(filename);

    generatePhotoThumbnail(saveFilePath);
    badgeService.add(badge);
    return "redirect:list";
  }


  @RequestMapping("/delete")
  public String delete(int no) throws Exception {

    if (badgeService.delete(no) == 0) {
      throw new Exception("해당 번호의 뱃지 없습니다.");
    }
    return "redirect:list";
  }

  @RequestMapping("/detail")
  public ModelAndView deatil(int no) throws Exception {
    Badge badge = badgeService.get(no);

    if (badge == null) {
      throw new Exception("해당 뱃지가 없습니다!");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("badge", badge);
    mv.setViewName("/badge/detail.jsp");
    return mv;
  }

  @RequestMapping("list")
  public ModelAndView list() throws Exception {
    ModelAndView mv = new ModelAndView();

    mv.addObject("list", badgeService.list((String)null));
    mv.setViewName("/badge/list.jsp");
    return mv;

  }

  @RequestMapping("update")
  public String update(Badge badge) throws Exception {

    badgeService.update(badge);
    return "redirect:detail?no=" + badge.getNo();
  }

  @RequestMapping("/updatePhoto")
  public String updatePhoto(int no, Part photoFile) throws Exception {

    Badge badge = new Badge();
    badge.setNo(no);

    // 뱃지 사진 파일 저장
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String saveFilePath = servletContext.getRealPath("/upload/badge/" + filename);
      photoFile.write(saveFilePath);
      badge.setPhoto(filename);

      // 뱃지 사진의 썸네일 이미지 파일 생성하기
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
      .size(60, 60)//
      .outputFormat("png") //
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_60x60";
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
