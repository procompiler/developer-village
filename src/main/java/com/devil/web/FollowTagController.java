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
@RequestMapping("/follow/tag")
public class FollowTagController{
  @Autowired
  FollowService followService;
  @RequestMapping("add")
  public String add(Follow follow, HttpSession session, HttpServletRequest request)
      throws Exception {
    follow.setUserNo(((User)session.getAttribute("loginUser")).getNo());

    followService.addTag(follow);
    return "redirect:" + request.getHeader("Referer");

  }

  @RequestMapping("delete")
  protected String delete(Follow follow, HttpSession session, HttpServletRequest request)
      throws Exception {

    follow.setUserNo(((User)session.getAttribute("loginUser")).getNo());

    followService.deleteTag(follow);
    return "redirect:" + request.getHeader("Referer");
  }
}
