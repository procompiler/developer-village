package com.devil.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
  @Autowired UserService userService;
  @Autowired ServletContext servletContext;
  @Autowired FollowService followService;

  @RequestMapping("form")
  public ModelAndView form() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/user/form.html");
    return mv;
  }

  @RequestMapping("add")
  public String add(
      String email, String nickname, String name,
      String password, String loginType) throws Exception {

    User user = new User();
    user.setEmail(email);
    user.setNickname(nickname);
    user.setName(name);
    user.setPassword(password);
    user.setLoginType(loginType);

    userService.add(user);
    return "redirect:../../index.jsp";
  }

  @RequestMapping("delete")
  public String delete(int no) throws Exception {

    if (userService.delete(no) == 0) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }
    return "redirect:list";
  }
  
    @RequestMapping("detail")
    public ModelAndView detail(int no, HttpSession session) throws Exception {
  
      User user = userService.get(no);
      if (user == null) {
        throw new Exception("해당 번호의 유저가 없습니다!");
      }
  
      ModelAndView mv = new ModelAndView();
      mv.addObject("user", user);
  
      Map<String, Object> map = new HashMap<>();
      map.put("userNo", ((User)session.getAttribute("loginUser")).getNo());
      map.put("followeeNo", no);
      if (followService.getUser(map) != null) {
        session.setAttribute("followed", true);
      } else {
        session.setAttribute("followed", false);
      }
  
      mv.setViewName("/user/detail.jsp");
      return mv;
    }
  
    @RequestMapping("list")
    public ModelAndView list(String keyword, HttpSession session) throws Exception {
  
      ModelAndView mv = new ModelAndView();
  
      mv.addObject("list", userService.list(keyword));
  
      mv.addObject("followingUsers", userService.list((User)session.getAttribute("loginUser")));
      mv.setViewName("/user/list.jsp");
  
      return mv;
    }

  @RequestMapping("update")
  public String update(User user) throws Exception {
    userService.update(user);
    return "redirect:list";
  }

  @RequestMapping("/user/updatePhoto")
  public String updatePhoto(int no, Part photoFile) throws Exception {

    User user = new User();
    user.setNo(no);

    // 회원 사진 파일 저장
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String saveFilePath = servletContext.getRealPath("/upload/user/" + filename);
      photoFile.write(saveFilePath);
      user.setPhoto(filename);

      // 회원 사진의 썸네일 이미지 파일 생성하기
      generatePhotoThumnail(saveFilePath);
    }

    if (user.getPhoto() != null) {
      throw new Exception("사진을 선택하지 않았습니다.");
    }

    userService.update(user);
    return "redirect:detail?no=" + user.getNo();
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
      .size(60, 60)//
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