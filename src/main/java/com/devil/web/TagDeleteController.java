package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.service.TagService;

@Controller
public class TagDeleteController {
  TagService tagService;
  public TagDeleteController(TagService tagService) {
    this.tagService = tagService;
  }

  @RequestMapping("/tag/delete")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      int no = Integer.parseInt(request.getParameter("no"));

      if (tagService.delete(no) == 0) {
        throw new Exception("해당 번호의 태그가 없습니다.");
      }
      return "redirect:list";
    }
  }

