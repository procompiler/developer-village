package com.devil.web.app;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.devil.domain.Bookmark;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.BookmarkService;

@Controller
@RequestMapping("/bookmark")
@SessionAttributes("loginUser")
public class BookmarkController {

  @Autowired
  BookmarkService bookmarkService;
  @Autowired
  ArticleService articleService;

  @RequestMapping("add")
  public String add(Bookmark bookmark, @ModelAttribute("loginUser") User loginUser, HttpServletRequest request) throws Exception {
    bookmark.setUserNo(loginUser.getNo());
    bookmarkService.add(bookmark);
    return "redirect:" + request.getHeader("Referer");
  }

  @RequestMapping("delete")
  public String delete(Bookmark bookmark, @ModelAttribute("loginUser") User loginUser, HttpServletRequest request) throws Exception {
    bookmark.setUserNo(loginUser.getNo());
    bookmarkService.delete(bookmark);
    return "redirect:" + request.getHeader("Referer");
  }

  @RequestMapping("list")
  public void list(@ModelAttribute("loginUser") User loginUser, Model model) throws Exception {
    model.addAttribute("bookmarkList", articleService.bookmarkList(loginUser));
  }
}
