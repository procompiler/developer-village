package com.devil.web;

import java.beans.PropertyEditorSupport;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.devil.domain.Comment;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  ArticleService articleService;
  @Autowired
  CommentService commentService;

  @RequestMapping(value = "/add", method=RequestMethod.POST)
  public String add(int arno, int step, int momno, String content, HttpSession session) throws Exception {

    Comment comment = new Comment();

    User user = (User) session.getAttribute("loginUser");
    System.out.println(user.getName());
    System.out.println(user.getNo());
    //System.out.println(user.getNickname());

    comment.setWriter(user);

    System.out.println(comment.getWriter().getName());

    comment.setArticleNo(arno);
    comment.setStep(step);
    comment.setMotherNo(momno);
    comment.setContent(content);
    commentService.add(comment);

    return "redirect:../article/detail?no=" + comment.getArticleNo();
  }

  @RequestMapping("/list")
  public ModelAndView list(int no) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("article", articleService.get(no));
    mv.addObject("comments", commentService.getByArticleNo(no));
    mv.setViewName("/comment/list.jsp");

    return mv;
  }

  @RequestMapping("/writtenList")
  public ModelAndView list(HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("commentList", commentService.listByWriter((User)session.getAttribute("loginUser")));
    mv.setViewName("/comment/writtenList.jsp");
    return mv;
  }

  @RequestMapping("/update")
  public String update(Comment comment) throws Exception {
    if (commentService.update(comment) == 0) {
      throw new Exception("해당 댓글이 없습니다.");
    }

    return "redirect:../article/detail?no=" + comment.getArticleNo();
  }

  @RequestMapping("/delete")
  public String delete(int no, int articleNo) throws Exception {
    if (commentService.delete(no) == 0) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }

    return "redirect:../article/detail?no=" + articleNo;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    // String ===> Date 프로퍼티 에디터 준비
    DatePropertyEditor propEditor = new DatePropertyEditor();

    // WebDataBinder에 프로퍼티 에디터 등록하기
    binder.registerCustomEditor(java.util.Date.class, // String을 Date 타입으로 바꾸는 에디터임을 지정한다.
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
