package com.devil.web.app;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Article;
import com.devil.domain.Comment;
import com.devil.domain.Report;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.service.CommentService;
import com.devil.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
  @Autowired ArticleService articleService;
  @Autowired ReportService reportService;
  @Autowired CommentService commentService;

  @RequestMapping("reportArticle")
  public String reportArticle(int no, Model model) throws Exception {
    model.addAttribute("reportedArticle", articleService.get(no));
    return "report/report-article";
  }

  @RequestMapping("reportArticle-send")
  public String reportArticleSend(Report report, int articleNo, String reason, HttpSession session) throws Exception {

    Article reportedArticle = new Article();
    reportedArticle.setNo(articleNo);

    User reporter = (User) session.getAttribute("loginUser");
    report.setReportedArticle(reportedArticle);
    report.setReporter(reporter);
    report.setReportTypeNo(Integer.parseInt(reason));
    reportService.reportArticle(report);

    return "redirect:../article/" + reportedArticle.getNo();
  }

  @RequestMapping("reportComment")
  public String reportComment(int no, Model model) throws Exception {
    model.addAttribute("reportedComment", commentService.get(no));
    return "report/report-comment";
  }

  @RequestMapping("reportComment-send")
  public String reportCommentSend(Report report, int commentNo, int commentArticleNo, String reason, HttpSession session) throws Exception {

    Comment reportedComment = new Comment();
    reportedComment.setNo(commentNo);
    reportedComment.setArticleNo(commentArticleNo);

    User reporter = (User) session.getAttribute("loginUser");

    report.setReportedComment(reportedComment);
    report.setReporter(reporter);
    report.setReportTypeNo(Integer.parseInt(reason));

    reportService.reportComment(report);
    return "redirect:../article/" + reportedComment.getArticleNo();

  }

}
