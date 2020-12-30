package com.devil.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.devil.domain.Article;
import com.devil.service.ArticleService;
import com.devil.service.BookmarkService;
import com.devil.service.CommentService;
import com.devil.service.TagService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  ArticleService articleService;
  @Autowired
  TagService tagService;
  @Autowired
  BookmarkService bookmarkService;
  @Autowired
  UserService userService;
  @Autowired
  CommentService commentService;

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

    model.addAttribute("articles", articleService.adminList(keyword, pageNo, pageSize));

    int size = articleService.size(keyword);
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

  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {
    Article article = articleService.get(no);
    if (article == null) {
      throw new Exception("해당 게시글이 없습니다.");
    }

    model.addAttribute("article", article);
    model.addAttribute("tags", article.getTags());
    model.addAttribute("comments", commentService.getByArticleNo(no));

    return "article/detail";
  }

  @GetMapping("inactivate")
  public String inactivate(int no) throws Exception {
    if (articleService.delete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:/admin/article/" + no;
  }

  @GetMapping("activate")
  public String activate(int no) throws Exception {
    if (articleService.undelete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:/admin/article/" + no;
  }

}
