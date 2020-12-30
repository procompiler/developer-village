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
import org.springframework.web.bind.annotation.RequestParam;
import com.devil.domain.User;
import com.devil.service.FollowService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  ServletContext servletContext;
  @Autowired
  FollowService followService;

  @GetMapping("/delete")
  public String delete(int no) throws Exception {

    if (userService.delete(no) == 0) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }
    return "redirect:.";
  }

  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model, HttpSession httpSession)
      throws Exception {
    User loginUser = (User) httpSession.getAttribute("loginUser");
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "admin");
    params.put("userNo", no);
    User user = userService.get(params);
    model.addAttribute("user", user);
    System.out.println(loginUser.getNo());

    if (user == null) {
      throw new Exception("해당 번호의 유저가 없습니다!");
    }
    Map<String, Object> map = new HashMap<>();
    map.put("userNo", loginUser.getNo());
    return "user/detail";
  }

  @GetMapping("list")
  public void list(
      @RequestParam(defaultValue = "1") int type,
      String keyword,
      @RequestParam(defaultValue = "1") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      Model model) throws Exception {

    if (pageNo < 1) {
      pageNo = 1;
    }
    if (pageSize < 3 || pageSize > 100) {
      pageSize = 5;
    }

    model.addAttribute("list", userService.adminList(keyword, pageNo, pageSize));

    int size = userService.size(keyword);
    int totalPage = size / pageSize;
    if (size % pageSize > 0) {
      totalPage++;
    }

    int prevPageNo = pageNo > 1 ? pageNo - 1 : 1;
    int nextPageNo = pageNo + 1;
    if (nextPageNo > totalPage) {
      nextPageNo = totalPage;
    }

    model.addAttribute("currPageNo", pageNo);
    model.addAttribute("prevPageNo", prevPageNo);
    model.addAttribute("nextPageNo", nextPageNo);
    model.addAttribute("totalPage", nextPageNo);
    model.addAttribute("size", size);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("keyword", keyword);

  }

  @GetMapping("inactivate")
  public String inactivate(int no) throws Exception {
    if (userService.delete(no) == 0) {
      throw new Exception("해당 번호의 유저가 없습니다.");
    }
    return "redirect:/admin/user/" + no;
  }

  @GetMapping("activate")
  public String activate(int no) throws Exception {
    if (userService.undelete(no) == 0) {
      throw new Exception("해당 번호의 유저가 없습니다.");
    }
    return "redirect:/admin/user/" + no;
  }

}
