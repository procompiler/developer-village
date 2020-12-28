package com.devil.web.admin;

import java.io.File;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.devil.domain.Badge;
import com.devil.service.BadgeService;
import com.devil.service.BadgeStanService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("/badge")
public class BadgeController {

  @Autowired ServletContext servletContext;
  @Autowired BadgeService badgeService;
  @Autowired BadgeStanService badgeStanService;

  @GetMapping("/form")
  public void form() {
  }

  @RequestMapping("add")
  public String add(String name, String content,MultipartFile photoFile) throws Exception {

    String filename = UUID.randomUUID().toString();
    String saveFilePath = servletContext.getRealPath("/upload/badge/" + filename);

    Badge badge = new Badge();
    badge.setName(name);
    badge.setContent(content);

    photoFile.transferTo(new File(saveFilePath));
    badge.setPhoto(filename);

    generatePhotoThumbnail(saveFilePath);

    badgeService.add(badge);
    return "redirect:list";
  }


  @GetMapping("delete")
  public String delete(int no) throws Exception {

    if (badgeService.delete(no) == 0) {
      throw new Exception("해당 번호의 뱃지 없습니다.");
    }
    return "redirect:list";
  }
 
  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {
    Badge badge = badgeService.get(no);

    if (badge == null) {
      throw new Exception("해당 뱃지가 없습니다!");
    }

    model.addAttribute("badge", badge);
    model.addAttribute("badgeStans", badgeStanService.list(badge.getNo()));
    return "badge/detail";
  }
  
  /*
  @GetMapping("detail")
  public String detail(int no,Model model) throws Exception {
    Badge badge = badgeService.get(no);

    if (badge == null) {
      throw new Exception("해당 뱃지가 없습니다!");
    }

    model.addAttribute("badge", badge);
    return "badge/detail";
  }
   */
  @GetMapping("list")
  public String list(Model model, String keyword) throws Exception {
    model.addAttribute("badgeList", badgeService.list(keyword));
    return "badge/list";
  }
  /* 수여 기준
  @GetMapping("info")
  public String badgeStans(int no,Model model) throws Exception {
    Badge badge = badgeService.get(no);

    if (badge == null) {
      throw new Exception("해당 뱃지가 없습니다!");
    }

    model.addAttribute("badge", badge);
    return "badge/info";
  }
   */
  @RequestMapping("update")
  public String update(Badge badge) throws Exception {
    
    badgeService.update(badge);
    return "redirect:./" + badge.getNo();
  }
  
  
  @RequestMapping("updatePhoto")
  public String updatePhoto(int no, MultipartFile photoFile) throws Exception {

    Badge badge = new Badge();
    badge.setNo(no);

    // 뱃지 사진 파일 저장
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String saveFilePath = servletContext.getRealPath("/upload/badge/" + filename);
      photoFile.transferTo(new File(saveFilePath));
      badge.setPhoto(filename);

      // 뱃지 사진의 썸네일 이미지 파일 생성하기
      generatePhotoThumbnail(saveFilePath);
    }

    if (badge.getPhoto() == null) {
      throw new Exception("사진을 선택하지 않았습니다.");
    }

    badgeService.update(badge);
    return "redirect:." + badge.getNo();
  }

  private void generatePhotoThumbnail(String saveFilePath) {
    try {
      Thumbnails.of(saveFilePath)//
      .size(60, 60)//
      .crop(Positions.CENTER)
      .outputFormat("png")//
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_60x60";
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
