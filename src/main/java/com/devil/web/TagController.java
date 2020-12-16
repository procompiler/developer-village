package com.devil.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.TagService;

@Controller
@RequestMapping("/tag")
public class TagController {

  @Autowired
  ServletContext servletContext;

  @Autowired
  TagService tagService;

  @RequestMapping("/list")
  public ModelAndView list(HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");

    List<Tag> tagList = tagService.list((String) null);
    List<Integer> userTagNoList = new ArrayList<>();
    for (Tag tag : tagService.list(loginUser)) {
      userTagNoList.add(tag.getNo());
    }

    for (Tag tag : tagList) {
      if (!userTagNoList.contains(tag.getNo())) {
        continue;
      }
      tag.setFollowed(true);
    }
    ModelAndView mv = new ModelAndView();
    mv.addObject("tagList", tagList);
    mv.setViewName("/tag/list.jsp");
    return mv;
  }
}
