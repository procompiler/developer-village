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
      Elements elements = positionDoc.select("table.table-information tbody tr ");
      System.out.println("====================");
      System.out.printf("제목: %s\n", positionDoc.select("h2.title").text());
      System.out.printf("회사명: %s\n", positionDoc.select("h4.sub-title").text());
      for (Element ele : elements) {
        String raw = ele.text();
        if (raw.startsWith("기간 ")) {
          //System.out.println(raw);
          String parsed = raw.replace("기간 ", "");
          if (raw.contains("까지") && raw.contains("부터")) {
            parsed = raw.split(" ")[4];
          }
          System.out.printf("기간: %s\n", parsed);
          break;
        }
      }
      System.out.println("내용: ");
      Element element = positionDoc.select("div.page-show-detail").select("div.content-body").get(0);
      Elements sections = new Elements();
      sections.addAll(element.select("section.section-position"));
      sections.addAll(element.select("section.section-requirements"));
      sections.addAll(element.select("section.section-preference"));
      for (Element section : sections) {
        String title = section.select("h5.section-title").text();
        System.out.println("[" + section.select("h5.section-title").text()+ "]");
        Elements lis = section.select("li");
        for (Element li : lis) {
          System.out.println("   " + li.text());;
        }
        if (lis.size() == 0) {
          for (Element p : section.select("p")) {
            System.out.println(p.text());            
          }
        }
        
      }
      System.out.println("====================");
    }

  }

}


