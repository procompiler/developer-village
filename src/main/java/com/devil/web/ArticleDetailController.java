package com.devil.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Article;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.BookmarkService;

@Controller
public class ArticleDetailController {

  @Autowired
  ArticleService articleService;
  @Autowired
  BookmarkService bookmarkService;

  @RequestMapping("/article/detail")
  protected ModelAndView doGet(int no, HttpSession session)
      throws Exception {

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
      session.setAttribute("bookmarked", true);
    } else {
      session.setAttribute("bookmarked", false);
    }

    mv.setViewName("/article/detail.jsp");
    return mv;
  }
}
