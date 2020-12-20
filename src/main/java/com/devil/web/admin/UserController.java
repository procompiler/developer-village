package com.devil.web.admin;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.User;
import com.devil.service.FollowService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired UserService userService;
  @Autowired ServletContext servletContext;
  @Autowired FollowService followService;

  @RequestMapping("/delete")
  public String delete(int no) throws Exception {

    if (userService.delete(no) == 0) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }
    return "redirect:list";
  }

  @RequestMapping("/detail")
  public ModelAndView detail(int no, HttpSession session, HttpServletRequest request) throws Exception {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "admin");
    params.put("userNo", no);
    User user = userService.get(params);
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

    mv.setViewName("/adminJsp/user/detail.jsp");
    return mv;
  }

  @RequestMapping("/list")
  public ModelAndView list(String keyword, HttpSession session) throws Exception {

    ModelAndView mv = new ModelAndView();

    mv.addObject("list", userService.list(keyword));

    mv.setViewName("/adminJsp/user/list.jsp");

    return mv;
  }
}