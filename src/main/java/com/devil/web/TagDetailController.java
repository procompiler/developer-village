package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Tag;
import com.devil.service.TagService;

@Controller
public class TagDetailController {
  TagService tagService;
  
  public TagDetailController(TagService tagService) {
    this.tagService = tagService;
  }

  @RequestMapping("/tag/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    int no = Integer.parseInt(request.getParameter("no"));
    Tag tag = tagService.get(no);
    if (tag == null) {
      throw new Exception("해당 태그가 없습니다!");
    }
    request.setAttribute("tag", tag);
    return "/tag/detail.jsp";
  }
}


