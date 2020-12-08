package com.devil.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.devil.dao.ArticleDao;
import com.devil.dao.BadgeDao;
import com.devil.dao.BlockDao;
import com.devil.dao.BookmarkDao;
import com.devil.dao.CommentDao;
import com.devil.dao.FollowDao;
import com.devil.dao.ReportDao;
import com.devil.dao.TagDao;
import com.devil.dao.UserDao;
import com.devil.dao.mariadb.ArticleDaoImpl;
import com.devil.dao.mariadb.BadgeDaoImpl;
import com.devil.dao.mariadb.BlockDaoImpl;
import com.devil.dao.mariadb.BookmarkDaoImpl;
import com.devil.dao.mariadb.CommentDaoImpl;
import com.devil.dao.mariadb.FollowDaoImpl;
import com.devil.dao.mariadb.ReportDaoImpl;
import com.devil.dao.mariadb.TagDaoImpl;
import com.devil.dao.mariadb.UserDaoImpl;
import com.devil.service.ArticleService;
import com.devil.service.BadgeService;
import com.devil.service.BlockService;
import com.devil.service.BookmarkService;
import com.devil.service.CommentService;
import com.devil.service.DefaultArticleService;
import com.devil.service.DefaultBadgeService;
import com.devil.service.DefaultBlockService;
import com.devil.service.DefaultBookmarkService;
import com.devil.service.DefaultCommentService;
import com.devil.service.DefaultFollowService;
import com.devil.service.DefaultReportService;
import com.devil.service.DefaultTagService;
import com.devil.service.DefaultUserService;
import com.devil.service.FollowService;
import com.devil.service.ReportService;
import com.devil.service.TagService;
import com.devil.service.UserService;
import com.devil.util.SqlSessionFactoryProxy;

@WebListener
public class DataHandlerListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 시스템에서 사용할 객체를 준비한다.
    try {
      // Mybatis 객체 준비
      SqlSessionFactoryProxy sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(
              Resources.getResourceAsStream("com/devil/conf/mybatis-config.xml")));

      // DAO 구현체 생성
      ArticleDao articleDao = new ArticleDaoImpl(sqlSessionFactory);
      UserDao userDao = new UserDaoImpl(sqlSessionFactory);
      TagDao tagDao = new TagDaoImpl(sqlSessionFactory);
      BadgeDao badgeDao = new BadgeDaoImpl(sqlSessionFactory);
      CommentDao commentDao = new CommentDaoImpl(sqlSessionFactory);
      ReportDao reportDao = new ReportDaoImpl(sqlSessionFactory);
      BlockDao blockDao = new BlockDaoImpl(sqlSessionFactory);
      BookmarkDao bookmarkDao = new BookmarkDaoImpl(sqlSessionFactory);
      FollowDao followDao = new FollowDaoImpl(sqlSessionFactory);

      // Service 구현체 생성
      ArticleService articleService = new DefaultArticleService(articleDao, sqlSessionFactory);
      UserService userService = new DefaultUserService(userDao);
      TagService tagService = new DefaultTagService(tagDao);
      BadgeService badgeService = new DefaultBadgeService(badgeDao);
      CommentService commentService = new DefaultCommentService(commentDao);
      ReportService reportService = new DefaultReportService(reportDao, sqlSessionFactory);
      BlockService blockService = new DefaultBlockService(blockDao, userDao, reportDao, sqlSessionFactory);
      BookmarkService bookmarkService = new DefaultBookmarkService(bookmarkDao);
      FollowService followService = new DefaultFollowService(followDao);
      // 다른 객체가 사용할 수 있도록 context 맵 보관소에 저장해둔다.
      ServletContext ctx = sce.getServletContext();

      ctx.setAttribute("articleService", articleService);
      ctx.setAttribute("userService", userService);
      ctx.setAttribute("tagService", tagService);
      ctx.setAttribute("badgeService", badgeService);
      ctx.setAttribute("commentService", commentService);
      ctx.setAttribute("reportService", reportService);
      ctx.setAttribute("blockService", blockService);
      ctx.setAttribute("bookmarkService", bookmarkService);
      ctx.setAttribute("followService", followService);

    } catch (Exception e) {
      System.out.println("Mybatis 및 DAO, 서비스 객체 준비 중 오류 발생!");
      e.printStackTrace();
    }
  }

}
