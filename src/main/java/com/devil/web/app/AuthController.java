package com.devil.web.app;

import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.devil.domain.User;
import com.devil.service.BlockService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired UserService userService;
  @Autowired BlockService blockService;

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
    if (user == null || user.getState() == 0) {
      return "redirect:./loginError";
    }

    userService.updateLoginTimeStamp(user);

    session.setAttribute("loginUser", user);
    return "redirect:../../index.jsp";
  }

  @SuppressWarnings("unchecked")
  @PostMapping("googleLogin")
  public String googleLogin(
      String accessToken, HttpSession session) throws Exception {

    RestTemplate restTemplate = new RestTemplate();

    Map<String,Object> googleUserInfo = restTemplate.getForObject(
        "https://graph.facebook.com/v9.0/me?access_token={1}&fields={2}",
        Map.class,
        accessToken,
        "id,name,email,gender");

    // 사용자 정보 확인
    String email = (String) googleUserInfo.get("email");
    if (email == null) {
      session.invalidate();
      return "/auth/loginError.jsp";
    }

    User user = userService.get(email);
    if (user == null) {
      // 자동 회원 가입
      user = new User();
      user.setEmail(email);
      user.setPassword("1111");
      user.setName((String) googleUserInfo.get("name"));
      user.setLoginType("2");
      userService.add(user);

      // 회원 등록 후 DB의 등록된 회원 정보를 가져온다.
      session.setAttribute("loginUser", userService.get(email));

    } else {
      session.setAttribute("loginUser", user);
    }

    return "redirect:../../index.jsp";
  }
  
  /*
  @SuppressWarnings("unchecked")
  @PostMapping("githubLogin")
  public String githubLogin(
      String accessToken, HttpSession session) throws Exception {
    RestTemplate restTemplate = new RestTemplate();

    Map<String,Object> githubUserInfo = restTemplate.getForObject(
        "https://graph.facebook.com/v9.0/me?access_token={1}&fields={2}",
        Map.class,
        accessToken,
        "id,name,email,gender");

    // 사용자 정보 확인
    String email = (String) githubUserInfo.get("email");
    if (email == null) {
      session.invalidate();
      return "/auth/loginError.jsp";
    }

    User user = userService.get(email);
    if (user == null) {
      // 자동 회원 가입
      user = new User();
      user.setEmail(email);
      user.setPassword("1111");
      user.setName((String) githubUserInfo.get("name"));
      user.setLoginType("2");
      userService.add(user);

      // 회원 등록 후 DB의 등록된 회원 정보를 가져온다.
      session.setAttribute("loginUser", userService.get(email));

    } else {
      session.setAttribute("loginUser", user);
    }

    return "redirect:../../index.jsp";
  }
*/
  @GetMapping("loginError")
  public void loginError() {
  }

  @GetMapping("logout")
  public void logout(HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser != null) {
      session.invalidate(); // 로그아웃을 요청한 클라이언트의 세션을 무효화시킨다.
    }
    //return "redirect:../../index.jsp";
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