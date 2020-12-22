package com.devil.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.devil.domain.Article;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.BookmarkService;
import com.devil.service.CommentService;
import com.devil.service.TagService;
import com.devil.service.UserService;

@Controller
@RequestMapping("/article")
@SessionAttributes("loginUser")
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

  @GetMapping("form")
  public void form(Model model) throws Exception {
    model.addAttribute("tags", tagService.list((String) null));
  }

  @PostMapping("add")
  public String add(Article article,
      int[] tagNo,
      @ModelAttribute("loginUser") User loginUser) throws Exception {

    article.setWriter(loginUser);
    System.out.println("000111"+loginUser.getName());
    System.out.println("000111"+loginUser.getNo());

    List<Tag> tags = new ArrayList<>();
    if (tagNo != null) {
      for (int no : tagNo) {
        tags.add(new Tag().setNo(no));
      }
    }
    article.setTags(tags);
    articleService.add(article);
    return "redirect:list?categoryNo=" + article.getCategoryNo();
  }

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
      model.addAttribute("articles", articleService.list());
    }
  }

  @GetMapping("writtenList")
  public void writtenList(User user, Model model) throws Exception {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", user.getNo());

    model.addAttribute("user", userService.get(params));
    model.addAttribute("articleList", articleService.list(user));
  }

  @GetMapping("feed")
  public void feed(@ModelAttribute("loginUser") User loginUser, Model model) throws Exception {
    model.addAttribute("articleList", articleService.feedList(loginUser));
  }

  @GetMapping("detail")
  public void detail(int no, @ModelAttribute("loginUser") User loginUser, Model model) throws Exception {
    Article article = articleService.get(no);
    if (article == null) {
      throw new Exception("해당 게시글이 없습니다.");
    }

    model.addAttribute("article", article);
    model.addAttribute("tags", article.getTags());
    model.addAttribute("comments", commentService.getByArticleNo(no));

    Map<String, Object> map = new HashMap<>();
    map.put("userNo", loginUser.getNo());
    map.put("articleNo", no);
    if (bookmarkService.get(map) != null) {
      model.addAttribute("bookmarked", true);
    } else {
      model.addAttribute("bookmarked", false);
    }
  }

  @GetMapping("/update")
  public void updateForm(int no, @ModelAttribute("loginUser") User loginUser, Model model) throws Exception {

    Article article = articleService.get(no);

    // 자신이 작성한 글이 아니면 글을 수정하지 못하도록 조건문을 걸었음
    // 이후 interceptor로 구현해야 할 듯...!
    if (loginUser.getEmail().equalsIgnoreCase(article.getWriter().getEmail())) {
      model.addAttribute("article", articleService.get(no));
      model.addAttribute("tags", tagService.list((String) null));
    } else if (loginUser != article.getWriter()) {
      // 추후에 할 일 : 게시글 수정 권한이 없다는 알럿 띄우고 현재 페이지에 머물도록 수정하기
      throw new Exception("게시글 수정 권한이 없습니다.");
    }
  }

  @PostMapping("/update")
  public String update(Article article, int[] tagNo) throws Exception {

    List<Tag> tags = new ArrayList<>();
    if (tagNo != null) {
      for (int no : tagNo) {
        tags.add(new Tag().setNo(no));
      }
    }
    article.setTags(tags);

    if (articleService.update(article) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:detail?no=" + article.getNo();
  }

  @GetMapping("/delete")
  public String delete(int no) throws Exception {
    if (articleService.delete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:list"; // 커뮤니티 페이지 구현 후 수정 예정
  }

}
