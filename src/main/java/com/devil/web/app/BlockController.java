package com.devil.web.app;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.User;
import com.devil.service.BlockService;

@Controller
@RequestMapping("/block")
public class BlockController {
  @Autowired
  BlockService blockService;

  @GetMapping("info")
  public void info(HttpSession session, Model model) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    model.addAttribute("blockedUser", blockService.getByUser(loginUser.getNo()));
  }
}

