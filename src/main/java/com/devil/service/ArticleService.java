package com.devil.service;

import java.util.List;
import com.devil.domain.Article;

public interface ArticleService {
  int add(Article article) throws Exception;
  List<Article> list (String keyword) throws Exception;
}
