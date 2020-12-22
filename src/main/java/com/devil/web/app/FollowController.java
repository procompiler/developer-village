package com.devil.web.app;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.devil.domain.Follow;
import com.devil.domain.User;
import com.devil.service.FollowService;
import com.devil.service.TagService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/follow")
@SessionAttributes("loginUser")
public class FollowController{
  @Autowired
  FollowService followService;
  @Autowired
  TagService tagService;
  @Autowired
  UserService userService;


  @PostMapping("addTag")
  public String addTag(Follow follow, @ModelAttribute("loginUser") User loginUser, HttpServletRequest request)
      throws Exception {
    follow.setUserNo(loginUser.getNo());
    followService.addTag(follow);
    return "redirect:" + request.getHeader("Referer");

  }

  @GetMapping("deleteTag")
  public String deleteTag(Follow follow, @ModelAttribute("loginUser") User loginUser, HttpServletRequest request)
      throws Exception {

    follow.setUserNo(loginUser.getNo());

    followService.deleteTag(follow);
    return "redirect:" + request.getHeader("Referer");
  }

  @GetMapping("tagList")
  public void list(@ModelAttribute("loginUser") User loginUser, Model model) throws Exception {
    model.addAttribute("tagList", tagService.listByFollower(loginUser));
  }

  @PostMapping("addUser")
  public String addUser(Follow follow, @ModelAttribute("loginUser") User loginUser, HttpServletRequest request)
      throws Exception {
    follow.setUserNo(loginUser.getNo());
    followService.addUser(follow);
    return "redirect:" + request.getHeader("Referer");
  }

  @GetMapping("deleteUser")
  public String deleteUser(Follow follow, @ModelAttribute("loginUser") User loginUser, HttpServletRequest request)
      throws Exception {

    follow.setUserNo(loginUser.getNo());

    followService.deleteUser(follow);
    return "redirect:" + request.getHeader("Referer");
  }

  @GetMapping("userList")
  public void listUser(@ModelAttribute("loginUser") User loginUser, Model model) throws Exception {
    model.addAttribute("userList", userService.listFollowing(loginUser));
  }

  @GetMapping("followerList")
  public void listFollower(@ModelAttribute("loginUser") User loginUser, Model model) throws Exception {
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
  }
}
