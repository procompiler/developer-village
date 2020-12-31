package com.devil.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Article;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.BadgeService;
import com.devil.service.BookmarkService;
import com.devil.service.CommentService;
import com.devil.service.FollowService;
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
  @Autowired
  BadgeService badgeService;
  @Autowired
  FollowService followService;

  @GetMapping("form")
  public void form(Model model) throws Exception {
    model.addAttribute("tags", tagService.list((String) null));
  }

  @PostMapping("add")
  public String add(Article article,
      int[] tagNo,
      HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    article.setWriter(loginUser);

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
  public void list(String keyword, Integer categoryNo, Integer tagNo,Model model) throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put("keyword", keyword);

    if (keyword != null && categoryNo == null && tagNo == null) {
      model.addAttribute("articles", articleService.list(keyword));
    } else if (categoryNo != null) {
      map.put("categoryNo", categoryNo);
      model.addAttribute("articles", articleService.listByCategoryNoKeyword(map));
    } else if (tagNo != null) {
      map.put("tagNo", tagNo);
      model.addAttribute("tag", tagService.get(tagNo));
      model.addAttribute("articles", articleService.listByTagNoKeyword(map));
    } else {
      model.addAttribute("articles", articleService.list());
    }
  }

  @GetMapping("writtenList")
  public void writtenList(User user, Model model, HttpSession session) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", user.getNo());

    model.addAttribute("user", userService.get(params));
    model.addAttribute("articleList", articleService.list(user));
    model.addAttribute("badgeList", badgeService.list(user));
    User loginUser = (User) session.getAttribute("loginUser");
    Map<String, Object> map = new HashMap<>();
    map.put("userNo", loginUser.getNo());
    map.put("followeeNo", user.getNo());

    if (followService.getUser(map) != null) {
      session.setAttribute("followed", true);
    } else {
      session.setAttribute("followed", false);
    }
  }

  @GetMapping("feed")
  public void feed(HttpSession session, Model model) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    model.addAttribute("badgeList", badgeService.list(loginUser));
    model.addAttribute("articleList", articleService.feedList(loginUser));
  }

  @GetMapping("{no}")
  public String detail(@PathVariable int no, HttpSession session, Model model) throws Exception {
    Article article = articleService.get(no);
    if (article == null) {
      throw new Exception("해당 게시글이 없습니다.");
    }

    model.addAttribute("article", article);
    model.addAttribute("tags", article.getTags());
    model.addAttribute("comments", commentService.getByArticleNo(no));

    User loginUser = (User) session.getAttribute("loginUser");

    Map<String, Object> map = new HashMap<>();
    map.put("userNo", loginUser.getNo());
    map.put("articleNo", no);
    if (bookmarkService.get(map) != null) {
      model.addAttribute("bookmarked", true);
    } else {
      model.addAttribute("bookmarked", false);
    }
    return "article/detail";
  }

  @GetMapping("update")
  public void updateForm(int no, HttpSession session, Model model) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
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

  @PostMapping("update")
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

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (articleService.delete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:list"; // 커뮤니티 페이지 구현 후 수정 예정
  }

}
