package com.devil.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.devil.domain.Article;
import com.devil.domain.Comment;
import com.devil.domain.User;
import com.devil.service.ArticleService;
import com.devil.util.Prompt;
import com.devil.web.filter.Command;

public class CrawlComuArticleServlet implements Command{
  ArticleService articleService;

  public CrawlComuArticleServlet(ArticleService articleService) {
    this.articleService = articleService;
  }

  @Override
  public void execute(Map<String,Object> context) {
    int input = Prompt.inputInt("몇 개의 데이터를 입력하시겠습니까?");
    System.out.println(input + "개의 데이터를 입력합니다.");

    ArrayList<String> urls = new ArrayList<>();
    String url ="https://okky.kr/articles/life?offset=5000&max=+" + input + "&sort=id&order=desc"; //크롤링할 url지정
    Document listDoc = null; //Document에 페이지의 전체 소스 저장

    try {
      listDoc = Jsoup.connect(url).get();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Elements titles = listDoc.select("h5.list-group-item-heading a");
    for (Element element : titles) {
      urls.add("https://okky.kr" + element.attr("href"));
    }


    int totalArticles = urls.size();
    System.out.println("사용자가 입력한 숫자: " + input);
    System.out.println("실제 insert될 데이터: " + (urls.size()-2));

    for (int i = 2; i < totalArticles; i++) {
      Document doc = null;
      try {
        doc = Jsoup.connect(urls.get(i)).get();
      } catch (IOException e) {
        e.printStackTrace();
      }
      String writer = doc.selectFirst("a.nickname").attr("title");
      StringBuilder content = new StringBuilder();
      Article article = new Article();
      article.setTitle(doc.select("h2.panel-title").text());
      Elements ps = doc.selectFirst("article.content-text").select("p");
      for (Element p : ps) {
        content.append(p.text() + "\n");
      }
      article.setContent(content.toString());
      System.out.println(doc.selectFirst("span.timeago").text());
      article.setCreatedDate();
      User user = new User();
      //user.setNo(s)
      System.out.println("===========================");
      System.out.println("제목: " + article.getTitle());
      System.out.println("내용: " + article.getContent());
      System.out.println("작성일: " + article.getCreatedDate());

      //List<Comment> comments = new ArrayList<>();
      System.out.println("[댓글]");
      Elements lis = doc.select("ul.list-group").select("li.note-item");
      for (Element li : lis) {
        Comment comment = new Comment();
        String commentWriter = li.select("div.avatar-info").select("a.nickname").attr("title");

        if (commentWriter.equals(writer)) {
          comment.setWriter(get)
        } else {
          System.out.print("다른이: ");
        }

        comment.setCreatedDate(li.select("span.timeago").text());
        comment.setContent(li.select("article.list-group-item-text").text());
        System.out.println(comment.getContent());
      }
      System.out.println("===========================");

    }
    

  }
  public int getRandomNo(int until, int exclusiveNo) {
	  Random rand = new Random();
	  while (true) {
		  int result = rand.nextInt(until);
		  if (result != exclusiveNo)
			  return result;
		  continue;
	  }
  }
  
  public int getRandomNo(int until) {
	  Random rand = new Random();
	  return rand.nextInt(until);
  }
}



