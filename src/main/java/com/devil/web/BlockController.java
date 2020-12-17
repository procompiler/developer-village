package com.devil.web;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
  public ModelAndView form(int reportNo) throws Exception {
    Report report = reportService.get(reportNo);
    ModelAndView mv = new ModelAndView();
    mv.addObject("report", report);
    mv.setViewName("/block/form.jsp");
    return mv;
  }

  @RequestMapping("add")
  public String add(Block block, int reportNo) throws Exception {
    Report report = reportService.get(reportNo);
    block.setReport(report);
    blockService.block(block);
    return "redirect:list";
  }

  @RequestMapping("info")
  public ModelAndView info(HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    ModelAndView mv = new ModelAndView();
    mv.addObject("blockedUser", blockService.get(loginUser.getNo()));
    mv.setViewName("/block/info.jsp");
    return mv;
  }

  @RequestMapping("list")
  public ModelAndView list(String keyword) throws Exception {
    ModelAndView mv = new ModelAndView();
    if (keyword != null) {
      mv.addObject("blockList", blockService.list(keyword));
    } else {
      mv.addObject("blockList", blockService.list(null));
    }
    mv.setViewName("/block/blockList.jsp");
    return mv;
  }
}
