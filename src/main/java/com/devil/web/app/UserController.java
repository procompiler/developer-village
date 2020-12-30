package com.devil.web.app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
import com.devil.domain.User;
import com.devil.service.FollowService;
import com.devil.service.UserService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  ServletContext servletContext;
  @Autowired
  FollowService followService;

  @GetMapping("form")
  public void form() throws Exception {
  }

  @PostMapping("add")
  public String add(User user) throws Exception {
    userService.add(user);
    return "redirect:.";
  }

  @GetMapping("delete")
  public String delete(int no, HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    if (userService.delete(no) == 0) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }
    if (loginUser != null) {
      session.invalidate(); // 로그아웃을 요청한 클라이언트의 세션을 무효화시킨다.
    }
    return "redirect:../../index.jsp";
  }

  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model, HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", no);
    User user = userService.get(params);

    if (user == null) {
      throw new Exception("해당 번호의 유저가 없습니다!");
    }
    model.addAttribute("user", user);

    Map<String, Object> map = new HashMap<>();
    map.put("userNo", loginUser.getNo());
    map.put("followeeNo", no);

    if (followService.getUser(map) != null) {
      session.setAttribute("followed", true);
    } else {
      session.setAttribute("followed", false);
    }
    return "user/detail";
  }

  @GetMapping
  public String list(Model model, String keyword, HttpSession session) throws Exception {
    model.addAttribute("list", userService.list(keyword));
    model.addAttribute("followingUsers",userService.listFollowing((User) session.getAttribute("loginUser")));
    return "user/list";
  }

  @PostMapping("update")
  public String update(Model model, User user, HttpSession session) throws Exception {
    if (user.getNo() != ((User)session.getAttribute("loginUser")).getNo()) {
      throw new Exception("잘못된 접근입니다.");
    }

    if (userService.update(user) == 0) {
      throw new Exception("삭제된 회원입니다.");
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("type", "app");
      params.put("userNo", user.getNo());
      session.setAttribute("loginUser", userService.get(params));

      return "redirect:./" + user.getNo();
    }
  }

  @GetMapping("updatePwdForm")
  public void updatePwdForm(int no, Model model) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", no);
    model.addAttribute("user", userService.get(params));
  }

  @PostMapping("updatePwd")
  public String updatePwd(User user, int no, String password) throws Exception {
    userService.updatePwd(no, password);
    return "redirect:./" + user.getNo();
  }

  @GetMapping("updateForm")
  public void updateForm(Model model, int no) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", no);
    model.addAttribute("user", userService.get(params));
  }

  @PostMapping("updatePhoto")
  public String updatePhoto(int no, MultipartFile photoFile, HttpSession session) throws Exception {
    if (no != ((User)session.getAttribute("loginUser")).getNo()) {
      throw new Exception("잘못된 접근입니다.");
    }

    User user = new User();
    user.setNo(no);

    // 회원 사진 파일 저장
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String saveFilePath = servletContext.getRealPath("/upload/user/" + filename);
      photoFile.transferTo(new File(saveFilePath));
      user.setPhoto(filename);
      // 회원 사진의 썸네일 이미지 파일 생성하기
      generatePhotoThumbnail(saveFilePath);
    }

    if (user.getPhoto() == null) {
      throw new Exception("사진을 선택하지 않았습니다.");
    }

    userService.update(user);
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", user.getNo());
    session.setAttribute("loginUser", userService.get(params));
    return "redirect:./updateForm?no=" + user.getNo();
  }

  private void generatePhotoThumbnail(String saveFilePath) {
    try {

      Thumbnails.of(saveFilePath)
      .size(40, 40)
      .crop(Positions.CENTER).outputFormat("jpg")
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(saveFilePath)
      .size(60, 60)//
      .crop(Positions.CENTER).outputFormat("jpg")
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_60x60";
        }
      });

      Thumbnails.of(saveFilePath)
      .size(100, 100)//
      .crop(Positions.CENTER).outputFormat("jpg")
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });

      Thumbnails.of(saveFilePath)
      .size(160, 160).outputFormat("jpg").crop(Positions.CENTER).toFiles(new Rename() {
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
