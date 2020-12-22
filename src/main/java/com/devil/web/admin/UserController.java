package com.devil.web.admin;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.User;
import com.devil.service.FollowService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired UserService userService;
  @Autowired ServletContext servletContext;
  @Autowired FollowService followService;

  @GetMapping("/delete")
  public String delete(int no) throws Exception {

    if (userService.delete(no) == 0) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }
    return "redirect:.";
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
    model.addAttribute(
        "followingUsers",userService.listFollowing((User) session.getAttribute("loginUser")));

    return "user/list";
  }
}