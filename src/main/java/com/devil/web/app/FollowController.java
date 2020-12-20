package com.devil.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Follow;
import com.devil.domain.User;
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


  @RequestMapping("addTag")
  public String addTag(Follow follow, HttpSession session, HttpServletRequest request)
      throws Exception {
    follow.setUserNo(((User)session.getAttribute("loginUser")).getNo());

    followService.addTag(follow);
    return "redirect:" + request.getHeader("Referer");

  }

  @RequestMapping("deleteTag")
  public String deleteTag(Follow follow, HttpSession session, HttpServletRequest request)
      throws Exception {

    follow.setUserNo(((User)session.getAttribute("loginUser")).getNo());

    followService.deleteTag(follow);
    return "redirect:" + request.getHeader("Referer");
  }

  @RequestMapping("tagList")
  public ModelAndView list(HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("tagList", tagService.list((User)session.getAttribute("loginUser")));
    mv.setViewName("/appJsp/follow/tagList.jsp");
    return mv;
  }

  @RequestMapping("addUser")
  public String addUser(Follow follow, HttpSession session, HttpServletRequest request)
      throws Exception {
    follow.setUserNo(((User)session.getAttribute("loginUser")).getNo());

    followService.addUser(follow);
    return "redirect:" + request.getHeader("Referer");

  }

  @RequestMapping("deleteUser")
  public String deleteUser(Follow follow, HttpSession session, HttpServletRequest request)
      throws Exception {

    follow.setUserNo(((User)session.getAttribute("loginUser")).getNo());

    followService.deleteUser(follow);
    return "redirect:" + request.getHeader("Referer");
  }

  @RequestMapping("userList")
  public ModelAndView listUser(HttpSession session) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("user", (User)session.getAttribute("loginUser"));
    
    ModelAndView mv = new ModelAndView();
    mv.addObject("userList", userService.listFollowing(params));
    mv.setViewName("/appJsp/follow/userList.jsp");
    return mv;
  }

  @RequestMapping("followerList")
  public ModelAndView listFollower(HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("user", loginUser);

    List<User> followerList = userService.listFollower(params);
    List<Integer> userFollowingNoList = new ArrayList<>();
    for (User user : userService.listFollowing(params)) {
      userFollowingNoList.add(user.getNo());
    }

    for (User user : followerList) {
      if (!userFollowingNoList.contains(user.getNo())) {
        continue;
      }
      user.setFollowed(true);
    }
    
    ModelAndView mv = new ModelAndView();
    mv.addObject("userList", followerList);
    mv.setViewName("/appJsp/follow/followerList.jsp");
    return mv;
  }
}
