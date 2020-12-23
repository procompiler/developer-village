package com.devil.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.service.ArticleService;
import com.devil.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  ArticleService articleService;
  @Autowired
  CommentService commentService;

  @GetMapping("inactivate")
  public String inactivate(int no, int articleNo) throws Exception {
    if (commentService.delete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:/admin/article/detail?no=" + articleNo;
  }

  @GetMapping("activate")
  public String activate(int no, int articleNo) throws Exception {
    if (commentService.undelete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:/admin/article/detail?no=" + articleNo;
  }
}
