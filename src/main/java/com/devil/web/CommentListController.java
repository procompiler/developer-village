package com.devil.web;

import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.devil.service.ArticleService;
import com.devil.service.CommentService;

@Controller
public class CommentListController {

  @Autowired
  ArticleService articleService;
  @Autowired
  CommentService commentService;

  @RequestMapping("/comment/list")
  protected ModelAndView doGet(int no) throws Exception {

    ModelAndView mv = new ModelAndView();
    mv.addObject("article", articleService.get(no));
    mv.addObject("comments", commentService.getByArticleNo(no));
    mv.setViewName("/comment/list.jsp");

    return mv;
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
