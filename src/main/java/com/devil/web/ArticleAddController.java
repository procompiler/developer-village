package com.devil.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Article;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.ArticleService;

@Controller
public class ArticleAddController {

  @Autowired
  ArticleService articleService;

  @RequestMapping("/article/add")
  protected String service(Article article, String[] selectedTags, HttpSession session)
      throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    article.setWriter(loginUser);

    List<Tag> tags = new ArrayList<>();
    if (selectedTags != null) {
      for (String tagNo : selectedTags) {
        tags.add(new Tag().setNo(Integer.parseInt(tagNo)));
      }
    }
    article.setTags(tags);
    articleService.add(article);
    return "redirect:list?categoryNo=" + article.getCategoryNo();

  }
}
