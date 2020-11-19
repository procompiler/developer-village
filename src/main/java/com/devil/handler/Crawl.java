package com.devil.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.devil.dao.ArticleDao;

public class Crawl implements Command{
  ArticleDao articleDao;

  public Crawl(ArticleDao articleDao) {
    this.articleDao = articleDao;
  }

  @Override
  public void execute(Map<String,Object> context) {

    ArrayList<String> urls = new ArrayList<>();

    int pageNo = 1;
    // 크롤링할 목록의 마지막 페이지 알아내기
    String url ="https://programmers.co.kr/job?page="; //크롤링할 url지정
    //System.out.println(url + 1);
    Document listDoc = null; //Document에 페이지의 전체 소스 저장


    try {
      listDoc = Jsoup.connect(url + 1).get();
    } catch (IOException e) {
      e.printStackTrace();
    }


    Elements pages = listDoc.select("ul.pagination li.page-item a");
    int lastPage = Integer.parseInt(pages.get(7).attr("href").split("=")[1]);
    System.out.println(lastPage);

    Document doc = null;    
    // 크롤링할 목록 한 페이지의 URL 알아내기
    for (int i = 1; i <= lastPage; i++) {
      try {
        doc = Jsoup.connect(url + i).get();
      } catch (IOException e) {
        e.printStackTrace();
      }
      Elements titles = doc.select("h5.position-title a");
      for (Element element : titles) {
        //System.out.println(element.attr("href"));
        urls.add("https://programmers.co.kr" + element.attr("href"));
      } 
    }
   
    
    for (String positionUrl : urls) {
      //System.out.println(positionUrl);

      Document positionDoc = null;
      try {
        positionDoc = Jsoup.connect(positionUrl).get();
      } catch (IOException e) {
        e.printStackTrace();
      }
      String rawDate = positionDoc.select("table.table-information tbody tr").text();
      System.out.println(rawDate);
      /*
      .get(4).text();
      String parsedDate = rawDate;
      if (rawDate.contains("까지") && rawDate.contains("부터")) {
        parsedDate = rawDate.split(" ")[3];
      }
      System.out.println(parsedDate);
      System.out.println(positionDoc.select("h2.title").text());
      System.out.println(positionDoc.select("h4.sub-title").text());
      */
    }

  }

}


