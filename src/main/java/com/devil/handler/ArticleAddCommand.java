package com.devil.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.devil.dao.ArticleDao;
import com.devil.domain.Article;

public class ArticleAddCommand implements Command {

  ArticleDao articleDao;

  public ArticleAddCommand(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  @Override
  public void execute(Map<String,Object> context) {
    int no = 200;
    System.out.println("[게시물 등록]");
    try {
      String URL = "http://www.saramin.co.kr/zf_user/search/recruit?search_area=main&search_done=y&search_optional_item=n&searchType=default_mysearch&searchword=%EA%B0%9C%EB%B0%9C%EC%9E%90&recruitPage=2&recruitSort=relation&recruitPageCount=" 
          + no 
          + "&inner_com_type=&company_cd=0%2C1%2C2%2C3%2C4%2C5%2C6%2C7%2C9%2C10&quick_apply=&except_read=";
      org.jsoup.Connection conn = Jsoup.connect(URL);
      Document doc = conn.get();
      //Element jobs = doc.select();
      Elements titles = doc.select("h2.job_tit a");
      Elements dates = doc.select("div.job_date span.date");
      //Elements companyName = doc.select("strong.corp_name a");
      titles.attr("title");
      System.out.println(titles.size());
      
      for (int i = 0; i < no; i++) {
        Article article = new Article();
        article.setNo(i + 1);
        article.setTitle(titles.get(i).attr("title"));
        article.setWriter(2);
        article.setEndDate(parseDate(dates.get(i).text()));
        article.setContent("http://www.saramin.co.kr" + titles.get(i).attr("href"));
        article.setCategoryNo(3);
        //article.setCompany(companyName.get(i).attr("title"));
        articleDao.insert(article);
      }
      System.out.println("크롤링이 끝났습니다! 입력된 데이터 개수:" + no);

    } catch(Exception e) {
      e.printStackTrace();
    }
    
  }
  
  public String parseDate(String rawDate) {
    if (!rawDate.split(" ")[0].equals("~")) {
      return "1996-09-02";
    }
    return "2020-" + rawDate.split(" ")[1].split("\\(")[0].replace("/", "-");
  }
}