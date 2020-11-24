package com.devil.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.devil.dao.ArticleDao;
import com.devil.dao.mariadb.ArticleDaoImpl;
import com.devil.service.ArticleService;
import com.devil.service.DefaultArticleService;
import com.devil.util.SqlSessionFactoryProxy;

@WebListener
public class DataHandlerListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 시스템에서 사용할 객체를 준비한다.
    try {
      System.out.println("데이터핸들러리스너,,,");
      // Mybatis 객체 준비
      SqlSessionFactoryProxy sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(
              Resources.getResourceAsStream("com/devil/conf/mybatis-config.xml")));

      // DAO 구현체 생성
      ArticleDao articleDao = new ArticleDaoImpl(sqlSessionFactory);

      // Service 구현체 생성
      ArticleService articleService = new DefaultArticleService(articleDao);

      // 다른 객체가 사용할 수 있도록 context 맵 보관소에 저장해둔다.
      ServletContext ctx = sce.getServletContext();

      ctx.setAttribute("articleService", articleService);

    } catch (Exception e) {
      System.out.println("Mybatis 및 DAO, 서비스 객체 준비 중 오류 발생!");
      e.printStackTrace();
    }
  }

}
