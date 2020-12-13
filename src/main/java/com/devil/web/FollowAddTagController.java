package com.devil.web;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Follow;
import com.devil.domain.User;
import com.devil.service.FollowService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)

@Controller
@RequestMapping("/follow/tag")
public class FollowAddTagController {

  @Autowired
  FollowService followService;

  @RequestMapping("/add")
  public String add(HttpSession session, Follow follow, HttpServletRequest request)
      throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");

    if (followService.addTag(follow) == 0) {
      throw new Exception("이미 팔로우하고 있는 태그입니다.");
    }

    return "redirect:" + request.getHeader("Referer");

  }
}
