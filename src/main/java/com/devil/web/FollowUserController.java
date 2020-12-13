package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Follow;
import com.devil.domain.User;
import com.devil.service.FollowService;

@Controller
@RequestMapping("/follow/user")
public class FollowUserController{
  @Autowired
  FollowService followService;
  @RequestMapping("add")
  public String add(Follow follow, HttpSession session, HttpServletRequest request)
      throws Exception {
    follow.setUserNo(((User)session.getAttribute("loginUser")).getNo());

    followService.addUser(follow);
    return "redirect:" + request.getHeader("Referer");

  }

  @RequestMapping("delete")
  public String delete(Follow follow, HttpSession session, HttpServletRequest request)
      throws Exception {

    follow.setUserNo(((User)session.getAttribute("loginUser")).getNo());

    followService.deleteUser(follow);
    return "redirect:" + request.getHeader("Referer");
  }
}
