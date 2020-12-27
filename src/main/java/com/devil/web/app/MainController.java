package com.devil.web.app;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Article;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.TagService;

@Controller
@RequestMapping
public class MainController {

  @Autowired
  ServletContext servletContext;

  @Autowired TagService tagService;
  @Autowired ArticleService articleService;


  @GetMapping("/main")
  public String initMain(HttpSession session, Model model) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");

    List<Tag> tagList = tagService.list((String) null);
    List<Integer> userTagNoList = new ArrayList<>();
    for (Tag tag : tagService.listByFollower(loginUser)) {
      userTagNoList.add(tag.getNo());
    }

    for (Tag tag : tagList) {
      if (!userTagNoList.contains(tag.getNo())) {
        continue;
      }
      tag.setFollowed(true);
    }
    model.addAttribute("tagList", tagList);

    List<Article> articleList = articleService.list();
    model.addAttribute("articleList", articleList);

    List<Article> qnaList = articleService.list(2);
    model.addAttribute("qnaList", qnaList);

    List<Article> studyList = articleService.list(4);
    model.addAttribute("studyList", studyList);
    return "main/main";
  }
}
