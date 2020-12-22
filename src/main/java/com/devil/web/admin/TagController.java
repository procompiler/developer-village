package com.devil.web.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.TagService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("/tag")
public class TagController {

  @Autowired
  ServletContext servletContext;

  @Autowired
  TagService tagService;

  @GetMapping("/form")
  public void form() throws Exception {
  }

  @RequestMapping("/add")
  public String add(String name, String tagColor, String fontColor, MultipartFile photoFile)
      throws Exception {
    String filename = UUID.randomUUID().toString();
    String saveFilePath = servletContext.getRealPath("/upload/tag/" + filename);

    Tag tag = new Tag();
    tag.setName(name);
    tag.setTagColor(tagColor);
    tag.setFontColor(fontColor);

    photoFile.transferTo(new File(saveFilePath));
    tag.setPhoto(filename);

    generatePhotoThumbnail(saveFilePath);
    tagService.add(tag);
    return "redirect:.";
  }

  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {
    Tag tag = tagService.get(no);
    
    if (tag == null) {
      throw new Exception("해당 태그가 없습니다!");
    }
    
    model.addAttribute("tag", tag);
    return "tag/detail";
  }

  @GetMapping("/list")
  public void list(HttpSession session, Model model) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");

    List<Tag> tagList = tagService.list((String) null);
    List<Integer> userTagNoList = new ArrayList<>();
    for (Tag tag : tagService.listByFollower(loginUser)) {
      userTagNoList.add(tag.getNo());
    }

    for (Tag tag : tagList) {
      if (!userTagNoList.contains(tag.getNo())) {
        continue;
      }
      tag.setFollowed(true);
    }
    model.addAttribute("tagList", tagList);
  }

  @PostMapping("/update")
  public String update(Tag tag) throws Exception {
    tagService.update(tag);
    return "redirect:./" + tag.getNo();
  }

  @PostMapping("/updatePhoto")
  public String updatePhoto(int no, MultipartFile photoFile) throws Exception {
    Tag tag = new Tag();
    tag.setNo(no);
    // 태그 사진 파일 저장
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String saveFilePath = servletContext.getRealPath("/upload/tag/" + filename);
      photoFile.transferTo(new File(saveFilePath));
      tag.setPhoto(filename);

      generatePhotoThumbnail(saveFilePath);
    }

    if (tag.getPhoto() == null) {
      throw new Exception("사진을 선택하지 않았습니다.");
    }

    tagService.update(tag);

    return "redirect:./" + tag.getNo();
  }

  @GetMapping("/delete")
  public String delete(int no) throws Exception {
    if (tagService.delete(no) == 0) {
      throw new Exception("해당 번호의 태그가 없습니다.");
    }
    return "redirect:.";
  }

  private void generatePhotoThumbnail(String saveFilePath) {
    try {
      Thumbnails.of(saveFilePath).size(40, 40).crop(Positions.CENTER).outputFormat("png")
          .toFiles(new Rename() {
            @Override
            public String apply(String name, ThumbnailParameter param) {
              return name + "_40x40";
            }
          });

      Thumbnails.of(saveFilePath).size(80, 80).outputFormat("png").toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(saveFilePath).size(160, 160).outputFormat("png").crop(Positions.CENTER)
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
