package com.devil.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Follow;
import com.devil.domain.User;
import com.devil.service.FollowService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)

@RequestMapping("/follow/tag")
public class FollowAddTagController{

  @Autowired FollowService followService;

  @RequestMapping("add")
  protected void execute(HttpSession session, Follow follow) {
    throws Exception {

      User loginUser = (User) session.getAttribute("loginUser");

      try {
        if (followService.addTag(follow) == 0) {
          throw new Exception("이미 팔로우하고 있는 태그입니다.");
        }
        response.sendRedirect(request.getHeader("Referer"));

      } catch (Exception e) {
        request.setAttribute("exception", e);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
        return;
      }
    }
  }
