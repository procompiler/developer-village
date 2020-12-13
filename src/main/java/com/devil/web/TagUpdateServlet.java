package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.devil.domain.Tag;
import com.devil.service.TagService;

@Controller
public class TagUpdateServlet {

  @Autowired
  TagService tagService;

  //@RequestMapping("/tag/update")
  public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Tag tag = new Tag();
    tag.setNo(Integer.parseInt(request.getParameter("no")));
    tag.setName(request.getParameter("name"));
    tag.setTagColor(request.getParameter("tagColor"));
    tag.setFontColor(request.getParameter("fontColor"));
    int count = tagService.update(tag);

    if (count == 0) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    response.sendRedirect("list");
  }
}
