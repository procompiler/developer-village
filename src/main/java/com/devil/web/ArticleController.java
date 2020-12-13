package com.devil.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Article;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.BookmarkService;
import com.devil.service.TagService;

@Controller
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  ArticleService articleService;
  @Autowired
  TagService tagService;
  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/form")
  public ModelAndView form() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("tags", tagService.list((String) null));
    mv.setViewName("/article/form.jsp");

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

  @RequestMapping("/list")
  public ModelAndView list(String keyword, String keywordTitle, String keywordWriter,
      String keywordTag) throws Exception {

    ModelAndView mv = new ModelAndView();

    if (keyword != null) {
      mv.addObject("articles", articleService.list(keyword));

    } else if (keywordTitle != null) {
      HashMap<String, Object> keywordMap = new HashMap<>();
      keywordMap.put("title", keywordTitle);
      keywordMap.put("writer", keywordWriter);
      keywordMap.put("tag", keywordTag);

      mv.addObject("articles", articleService.list(keywordMap));

    } else {
      mv.addObject("articles", articleService.list());
    }

    mv.setViewName("/article/list.jsp");
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

    Map<String, Object> map = new HashMap<>();
    map.put("userNo", ((User) session.getAttribute("loginUser")).getNo());
    map.put("articleNo", no);
    if (bookmarkService.get(map) != null) {
      request.setAttribute("bookmarked", true);
    } else {
      request.setAttribute("bookmarked", false);
    }

    mv.setViewName("/article/detail.jsp");
    return mv;
  }

  @RequestMapping(value="/update", method=RequestMethod.GET)
  public ModelAndView updateForm(int no) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("article", articleService.get(no));
    mv.addObject("tags", tagService.list((String) null));
    mv.setViewName("/article/update.jsp");
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


  @InitBinder
  public void initBinder(WebDataBinder binder) {
    // String ===> Date 프로퍼티 에디터 준비
    DatePropertyEditor propEditor = new DatePropertyEditor();

    // WebDataBinder에 프로퍼티 에디터 등록하기
    binder.registerCustomEditor(
        java.util.Date.class, // String을 Date 타입으로 바꾸는 에디터임을 지정한다.
        propEditor // 바꿔주는 일을 하는 프로퍼티 에디터를 등록한다.
        );
  }

  class DatePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      try {
        // 클라이언트가 텍스트로 보낸 날짜 값을 java.sql.Date 객체로 만들어 보관한다.
        setValue(java.sql.Date.valueOf(text));
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
  }

}
