package com.devil.web.filter;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.devil.domain.Article;
import com.devil.service.ArticleService;
import com.devil.util.Prompt;

public class CrawlRCArticleServlet implements Command{
  ArticleService articleService;

  public CrawlRCArticleServlet(ArticleService articleService) {
    this.articleService = articleService;
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
    int totalArticles = urls.size();
    int input = Prompt.inputInt(totalArticles + "개의 데이터를 입력할 수 있습니다. 몇 개의 데이터를 입력하시겠습니까?");
    System.out.println(input + "개의 데이터를 입력합니다.");
    for (int i = 0; i < input; i++) {
      Document positionDoc = null;
      try {
        positionDoc = Jsoup.connect(urls.get(i)).get();
      } catch (IOException e) {
        e.printStackTrace();
      }
      positionDoc.outputSettings(new OutputSettings().prettyPrint(false));

      Article article = new Article();
      String title, companyName, date = "";
      Elements elements = positionDoc.select("table.table-information tbody tr ");
      title = positionDoc.select("h2.title").text();
      companyName = positionDoc.select("h4.sub-title").text();
      article.setTitle(String.format("[%s]%s", companyName, title));
      System.out.println(article.getTitle());

      for (Element ele : elements) {
        String raw = ele.text();
        if (raw.startsWith("기간 ")) {
          String parsed = raw.replace("기간 ", "");
          if (raw.contains("까지") && raw.contains("부터")) {
            parsed = raw.split(" ")[4];
            date = parsed;
          } else {
            date = "2099-12-31";
          }
          break;
        }
      }
      article.setEndDate(Date.valueOf(date));

      StringBuilder content = new StringBuilder();
      Element element = positionDoc.select("div.page-show-detail").select("div.content-body").get(0);
      Elements sections = new Elements();
      sections.addAll(element.select("section.section-position"));
      sections.addAll(element.select("section.section-requirements"));
      sections.addAll(element.select("section.section-preference"));
      sections.addAll(element.select("section.section-description"));
      for (Element section : sections) {
        String sectionTitle = section.select("h5.section-title").text();
        if (sectionTitle.length() == 0) {
          sectionTitle = "회사소개";
        }
        content.append("## " + sectionTitle + "\n");
        // <li> 태그로 묶인 글 add.
        Elements lis = section.select("li");
        for (Element li : lis) {
          content.append("- " + li.wholeText() + "\n");
        }
        if (lis.size() == 0) {
          // <p> 태그로 묶인 글 add.
          for (Element p : section.select("p")) {
            content.append(p.wholeText() + "\n");
          }
        }
        content.append("\n");
      }
      article.setContent(content.toString());
      article.setCategoryNo(3);
      article.setWriter(2);
      // add
      try {
        articleService.add(article);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
}



