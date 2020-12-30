package com.devil.web.admin;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
  public void list(String keyword, String keywordTitle, String keywordWriter,
      String keywordTag, Integer tagNo, Model model) throws Exception {

    if (keyword != null) {
      model.addAttribute("articles", articleService.list(keyword));

    } else if (keywordTitle != null) {
      HashMap<String, Object> keywordMap = new HashMap<>();
      keywordMap.put("title", keywordTitle);
      keywordMap.put("writer", keywordWriter);
      keywordMap.put("tag", keywordTag);

      model.addAttribute("articles", articleService.list(keywordMap));

    } else if (tagNo != null) {
      model.addAttribute("tag", tagService.get(tagNo));
      model.addAttribute("articles", articleService.listByTagNo(tagNo));
    } else {
      model.addAttribute("articles", articleService.adminList());
    }
  }

  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {
    Article article = articleService.get(no);
    if (article == null) {
      throw new Exception("해당 게시글이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
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
