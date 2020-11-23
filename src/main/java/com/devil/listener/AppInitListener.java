package com.devil.listener;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.devil.context.ApplicationContextListener;
import com.devil.dao.ArticleDao;
import com.devil.dao.mariadb.ArticleDaoImpl;
import com.devil.handler.Command;
import com.devil.handler.Crawl;
import com.devil.service.ArticleService;
import com.devil.service.DefaultArticleService;
import com.devil.util.SqlSessionFactoryProxy;

public class AppInitListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String,Object> context) {
    System.out.println("DEVIL에 오신 것을 환영합니다!");

    // 시스템에서 사용할 객체를 준비한다.
    try {
      // Mybatis 객체 준비
      SqlSessionFactoryProxy sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(
              Resources.getResourceAsStream("com/devil/conf/mybatis-config.xml")));

      // DAO 구현체 생성
      ArticleDao articleDao = new ArticleDaoImpl(sqlSessionFactory);

      // Command 구현체 생성 및 commandMap 객체 준비
      Map<String,Command> commandMap = new HashMap<>();
      
      //commandMap.put("/article/add", new ArticleAddCommand(articleDao));
      ArticleService articleService = new DefaultArticleService(articleDao);
      commandMap.put("/crawl", new Crawl(articleService));
      context.put("commandMap", commandMap);
    } catch (Exception e) {
      System.out.println("시스템이 사용할 객체를 준비하는 중에 오류 발생");
      e.printStackTrace();
    }
  }
    

    @Override
    public void contextDestroyed(Map<String,Object> context) {
      System.out.println("프로젝트 관리 시스템(PMS)을 종료합니다!");
    }
    
    
}
