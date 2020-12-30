package com.devil.web.app;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Follow;
import com.devil.domain.User;
import com.devil.service.BadgeService;
import com.devil.service.FollowService;
import com.devil.service.TagService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/follow")
public class FollowController{
  @Autowired
  FollowService followService;
  @Autowired
  TagService tagService;
  @Autowired
  UserService userService;
  @Autowired
  BadgeService badgeService;
  
  @GetMapping("addTag")
  public String addTag(Follow follow, HttpSession httpSession, HttpServletRequest request)
      throws Exception {
    follow.setFollower((User) httpSession.getAttribute("loginUser"));
    followService.addTag(follow);
    return "redirect:" + request.getHeader("Referer");

  }

  @GetMapping("deleteTag")
  public String deleteTag(Follow follow, HttpSession httpSession, HttpServletRequest request)
      throws Exception {

    follow.setFollower((User) httpSession.getAttribute("loginUser"));

    followService.deleteTag(follow);
    return "redirect:" + request.getHeader("Referer");
  }

  @GetMapping("tagList")
  public void list(HttpSession httpSession, Model model) throws Exception {
    User loginUser = (User) httpSession.getAttribute("loginUser");
    model.addAttribute("tagList", tagService.listByFollower(loginUser));
    model.addAttribute("badgeList", badgeService.list(loginUser));
  }

  @GetMapping("addUser")
  public String addUser(Follow follow, HttpSession httpSession, HttpServletRequest request)
      throws Exception {
    follow.setFollower((User) httpSession.getAttribute("loginUser"));
    followService.addUser(follow);
    return "redirect:" + request.getHeader("Referer");
  }

  @GetMapping("deleteUser")
  public String deleteUser(Follow follow, HttpSession httpSession, HttpServletRequest request)
      throws Exception {
    follow.setFollower((User) httpSession.getAttribute("loginUser"));
    followService.deleteUser(follow);
    return "redirect:" + request.getHeader("Referer");
  }

  @GetMapping("userList")
  public void listUser(HttpSession httpSession, Model model) throws Exception {
    User loginUser = (User) httpSession.getAttribute("loginUser");
    model.addAttribute("userList", userService.listFollowing(loginUser));
    model.addAttribute("badgeList", badgeService.list(loginUser));
  }

  @GetMapping("followerList")
  public void listFollower(HttpSession httpSession, Model model) throws Exception {
    User loginUser = (User) httpSession.getAttribute("loginUser");
    List<User> followerList = userService.listFollower(loginUser);
    List<Integer> userFollowingNoList = new ArrayList<>();
    for (User user : userService.listFollowing(loginUser)) {
      userFollowingNoList.add(user.getNo());
    }

    for (User user : followerList) {
      if (!userFollowingNoList.contains(user.getNo())) {
        continue;
      }
      user.setFollowed(true);
    }
    model.addAttribute("userList", followerList);
    model.addAttribute("badgeList", badgeService.list(loginUser));
  }
}
