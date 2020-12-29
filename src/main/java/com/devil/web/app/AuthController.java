package com.devil.web.app;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.User;
import com.devil.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired UserService userService;

  @GetMapping("login")
  public void loginForm() throws Exception {
  }

  @PostMapping("login")
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
      return "/appJsp/auth/loginError.jsp";
    }
    userService.updateLoginTimeStamp(user);
    session.setAttribute("loginUser", user);
    return "redirect:../../index.jsp";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser != null) {
      session.invalidate(); // 로그아웃을 요청한 클라이언트의 세션을 무효화시킨다.
    }
    return "redirect:../../index.jsp";
  }

  @GetMapping("searchId")
  public void searchId() throws Exception {
  }

  @RequestMapping("searchId-send")
  public String searchId(String name, String tel, Model model) throws Exception {
    String userId;
    try {
      userId = userService.getId(name, tel).getEmail();
    } catch(Exception e) {
      userId = "";
    }
    return "redirect:./searchIdResult?userId=" + userId;
  }

  @GetMapping("searchIdResult")
  public void searchIdResult(String userId) throws Exception {
  }

  @GetMapping("searchPwd")
  public void searchPwd() throws Exception {
  }

  @PostMapping("searchPwd-send")
  public String searchPwd(String email, String name, String tel) throws Exception {
    int userNo;
    try {
      userNo = userService.getPwd(email, name, tel).getNo();
    } catch(Exception e) {
      userNo = -1;
    }
    return "redirect:./searchPwdResult?userNo=" + userNo ;
  }

  @GetMapping("searchPwdResult")
  public void searchPwdResult() throws Exception {
  }

  @PostMapping("searchPwdResult-send")
  public String searchPwdResult(int userNo, String password) throws Exception {
    userService.updatePwd(userNo, password);
    return "redirect:login";
  }

  @PostMapping("updatePwd")
  public String updatePwd(int userNo, String password) throws Exception {
    userService.updatePwd(userNo, password);
    return "redirect:login";
  }

  @PostMapping("updateInfo")
  public void updateInfo() throws Exception {
  }
}