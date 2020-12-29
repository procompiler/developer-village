package com.devil.web.admin;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Block;
import com.devil.domain.Report;
import com.devil.domain.User;
import com.devil.service.BlockService;
import com.devil.service.ReportService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/block")
public class BlockController {
  @Autowired
  BlockService blockService;
  @Autowired
  ReportService reportService;
  @Autowired
  UserService userService;

  @RequestMapping("form")
  public void form(int reportNo, Model model) throws Exception {
    Report report = reportService.get(reportNo);
    model.addAttribute("report", report);
  }

  @PostMapping("add")
  public String add(Block block, int reportNo, int reportLinkNo) throws Exception {
    //1) 신고번호로 무엇에 대한 신고인지 알아낸다.
    //2) 서비스객체에게 해당신고를 등록하라고 요구한다.
    Report report = reportService.get(reportNo);
    block.setReport(report);
    blockService.block(block, reportLinkNo);
    return "redirect:list";
  }

  @GetMapping("info")
  public void info(HttpSession session, Model model) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    model.addAttribute("blockedUser", blockService.get(loginUser.getNo()));
  }

  @GetMapping("list")
  public void list(String keyword, Model model) throws Exception {
    if (keyword != null) {
      model.addAttribute("blockList", blockService.list(keyword));
    } else {
      model.addAttribute("blockList", blockService.list(null));
    }
  }
}

