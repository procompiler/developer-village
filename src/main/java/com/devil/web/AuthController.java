package com.devil.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.User;
import com.devil.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired UserService userService;

  @RequestMapping(value="login", method = RequestMethod.GET)
  public ModelAndView loginForm(HttpServletRequest request) throws Exception {

    String email = "";

    Cookie[] cookies = request.getCookies();
    if(cookies != null) {
      for(Cookie cookie : cookies) {
        if(cookie.getName().equals("email")) {
          email = cookie.getValue();
          break;
        }
      }
    }
    ModelAndView mv = new ModelAndView();
    mv.addObject("email", email);
    mv.setViewName("/auth/form.jsp");
    return mv;
  }

  @RequestMapping(value="login", method = RequestMethod.POST)
  public String login(
      String email,
      String password,
      String saveEmail,
      HttpServletResponse response,
      HttpSession session) throws Exception {

    Cookie emailCookie = new Cookie("email", email);

    if (saveEmail != null) {
      emailCookie.setMaxAge(60 * 60 * 24 * 7);
    } else {
      emailCookie.setMaxAge(0);
    }

    response.addCookie(emailCookie);

    User user = userService.get(email, password);
    if (user == null) {
      return "/auth/loginError.jsp";
    }
    session.setAttribute("loginUser", user);
    return "redirect:../../index.jsp";

  }
  @RequestMapping("logout")
  public ModelAndView logout(HttpSession session, HttpServletResponse response) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser != null) {
      session.invalidate(); // 로그아웃을 요청한 클라이언트의 세션을 무효화시킨다.
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("loginUser", loginUser);
    mv.setViewName("/auth/logout.jsp");
    return mv;
  }

}