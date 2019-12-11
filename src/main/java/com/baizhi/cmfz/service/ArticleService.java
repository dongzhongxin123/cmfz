package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    List<Article> queryAll();

    Integer findByCount();

    Article findById(String id);

    void saveArticle(Article article);

    void deleteById(String id);

    void add(Article article);

    Map<String, Object> queryByPage(Integer page, Integer rows);

    //List<Map> search(String query, Integer pageIndex);

    public List<Article> querByHigh(String key);
}
