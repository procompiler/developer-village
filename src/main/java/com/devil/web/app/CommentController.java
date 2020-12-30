package com.devil.web.app;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Comment;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.BadgeService;
import com.devil.service.CommentService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  ArticleService articleService;
  @Autowired
  CommentService commentService;
  @Autowired
  UserService userService;
  @Autowired
  BadgeService badgeService;

  @PostMapping("add")
  public String add(Comment comment, HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    comment.setWriter(loginUser);
    commentService.add(comment);

    return "redirect:../article/" + comment.getArticleNo();
  }
  // article/detail에서 더이상 /comment/list를 직접 경유하지 않음
  // 추후에도 사용하지 않는다면 코드 삭제 예정
  @GetMapping("list")
  public void list(int no, Model model) throws Exception {
    model.addAttribute("article", articleService.get(no));
    model.addAttribute("comments", commentService.getByArticleNo(no));
  }

  @GetMapping("writtenList")
  public void list(User user, Model model) throws Exception {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", user.getNo());

    model.addAttribute("user", userService.get(params));
    model.addAttribute("commentList", commentService.listByWriter(user));
    model.addAttribute("badgeList", badgeService.list(user));
  }

  @PostMapping("update")
  public String update(Comment comment) throws Exception {
    if (commentService.update(comment) == 0) {
      throw new Exception("해당 댓글이 없습니다.");
    }
    return "redirect:../article/" + comment.getArticleNo();
  }

  @GetMapping("delete")
  public String delete(int no, int articleNo) throws Exception {
    if (commentService.delete(no) == 0) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }

    return "redirect:../article/" + articleNo;
  }

}