package com.devil.web.admin;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.BadgeStan;
import com.devil.service.BadgeStanService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("/badgeStan")
public class BadgeStanController {

  @Autowired ServletContext servletContext;
  @Autowired BadgeStanService badgeStanService;

  @RequestMapping("/form")
  public ModelAndView form() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/adminJsp/badgeStan/form.jsp");

    return mv;
  }

  @RequestMapping("add")
  public String add(String name, String content,Part photoFile) throws Exception {

    String filename = UUID.randomUUID().toString();
    String saveFilePath = servletContext.getRealPath("/upload/badgeStan/" + filename);

    BadgeStan badgeStan = new BadgeStan();
    badgeStan.setName(name);
    badgeStan.setContent(content);

    photoFile.write(saveFilePath);
    badgeStan.setPhoto(filename);

    generatePhotoThumbnail(saveFilePath);
    badgeStanService.add(badgeStan);
    return "redirect:list";
  }


  @RequestMapping("delete")
  public String delete(int no) throws Exception {

    if (badgeStanService.delete(no) == 0) {
      throw new Exception("해당 번호의 뱃지 없습니다.");
    }
    return "redirect:list";
  }

  @RequestMapping("detail")
  public ModelAndView deatil(int no) throws Exception {
    BadgeStan badgeStan = badgeStanService.get(no);

    if (badgeStan == null) {
      throw new Exception("해당 뱃지가 없습니다!");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("badgeStan", badgeStan);
    mv.setViewName("/adminJsp/badgeStan/detail.jsp");
    return mv;
  }

  @RequestMapping("list")
  public ModelAndView list() throws Exception {
    ModelAndView mv = new ModelAndView();

    mv.addObject("list", badgeStanService.list((String)null));
    mv.setViewName("/adminJsp/badgeStan/list.jsp");
    return mv;

  }

  @RequestMapping("update")
  public String update(BadgeStan badgeStan) throws Exception {

    badgeStanService.update(badgeStan);
    return "redirect:detail?no=" + badgeStan.getNo();
  }

  @RequestMapping("updatePhoto")
  public String updatePhoto(int no, Part photoFile) throws Exception {

    BadgeStan badgeStan = new BadgeStan();
    badgeStan.setNo(no);

    // 뱃지 사진 파일 저장
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String saveFilePath = servletContext.getRealPath("/upload/badgeStan/" + filename);
      photoFile.write(saveFilePath);
      badgeStan.setPhoto(filename);

      // 뱃지 사진의 썸네일 이미지 파일 생성하기
      generatePhotoThumbnail(saveFilePath);
    }

    if (badgeStan.getPhoto() == null) {
      throw new Exception("사진을 선택하지 않았습니다.");
    }

    badgeStanService.update(badgeStan);
    return "redirect:detail?no=" + badgeStan.getNo();
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
