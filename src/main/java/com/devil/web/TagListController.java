package com.devil.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Tag;
import com.devil.domain.User;
import com.devil.service.TagService;

@Controller
public class TagListController {
  TagService tagService;
  
  
  public TagListController(TagService tagService) {
    this.tagService = tagService;
  }
  
  @RequestMapping("/tag/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    User loginUser = (User) request.getSession().getAttribute("loginUser");

      List<Tag> list = tagService.list((String)null);
      List<Integer> userTagNoList = new ArrayList<>();
      for (Tag tag : tagService.list(loginUser)) {
        userTagNoList.add(tag.getNo());
      }
      request.setAttribute("list", list);
      request.setAttribute("userTagNoList", userTagNoList);
      return "/tag/list.jsp";
    }
  }
