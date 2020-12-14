package com.devil.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Comment;
import com.devil.service.CommentService;

@Controller
public class ReportCommentServlet {

  CommentService commentService;

  public ReportCommentServlet(CommentService commentService) {
    this.commentService = commentService;
  }

  @RequestMapping("/report/reportComment")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html;charset=UTF-8");

    int no = Integer.parseInt(request.getParameter("no"));
    Comment reportedComment = commentService.get(no);
    request.setAttribute("reportedComment", reportedComment);

    return "/report/report-comment.jsp";

  }
}
