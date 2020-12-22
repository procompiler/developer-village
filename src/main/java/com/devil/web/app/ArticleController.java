package com.devil.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

  @GetMapping("/form")
  public ModelAndView form() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("tags", tagService.list((String) null));
    mv.setViewName("/app/article/form.jsp");

    return mv;
  }

  @RequestMapping("/add")
  public String add(Article article, int[] tagNo, HttpSession session) throws Exception {

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
  public void list(
      String keyword,
      String keywordTitle,
      String keywordWriter,
      String keywordTag,
      Integer tagNo,
      Model model) throws Exception {

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

  @RequestMapping("/writtenList")
  public ModelAndView list(User user) throws Exception {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", "app");
    params.put("userNo", user.getNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("user", userService.get(params));
    mv.addObject("articleList", articleService.list(user));
    mv.setViewName("/appJsp/article/writtenList.jsp");
    return mv;
  }

  @RequestMapping("/feed")
  public ModelAndView list(HttpSession session) throws Exception {

    ModelAndView mv = new ModelAndView();
    mv.addObject("articleList", articleService.feedList((User)session.getAttribute("loginUser")));
    mv.setViewName("/appJsp/article/feed.jsp");
    return mv;
  }

  @RequestMapping("/detail")
  public ModelAndView detail(int no, HttpSession session, HttpServletRequest request) throws Exception {
    Article article = articleService.get(no);
    if (article == null) {
      throw new Exception("해당 게시글이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("article", article);
    mv.addObject("tags", article.getTags());
    mv.addObject("comments", commentService.getByArticleNo(no));

    Map<String, Object> map = new HashMap<>();
    map.put("userNo", ((User) session.getAttribute("loginUser")).getNo());
    map.put("articleNo", no);
    if (bookmarkService.get(map) != null) {
      request.setAttribute("bookmarked", true);
    } else {
      request.setAttribute("bookmarked", false);
    }

    mv.setViewName("/appJsp/article/detail.jsp");
    return mv;
  }

  @RequestMapping(value="/update", method=RequestMethod.GET)
  public ModelAndView updateForm(int no, HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    Article article = articleService.get(no);

    System.out.println(loginUser.getAuth());
    System.out.println(article.getWriter().getAuth());

    ModelAndView mv = new ModelAndView();

    if (loginUser.getNo() == article.getWriter().getNo()
        && loginUser.getEmail().equalsIgnoreCase(article.getWriter().getEmail())) {
      mv.addObject("article", articleService.get(no));
      mv.addObject("tags", tagService.list((String) null));
      mv.setViewName("/appJsp/article/update.jsp");
    } else if (loginUser != article.getWriter()) {
      // 게시글 수정 권한이 없다는 알럿 띄우기
      mv.setViewName("redirect:detail?no=" + article.getNo());
    }
    return mv;
  }

  @RequestMapping(value="/update", method=RequestMethod.POST)
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

  @RequestMapping("/delete")
  public String delete(int no) throws Exception {
    if (articleService.delete(no) == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    return "redirect:list"; // 커뮤니티 페이지 구현 후 수정 예정
  }

}
