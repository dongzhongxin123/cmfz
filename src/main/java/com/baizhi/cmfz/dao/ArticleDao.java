package com.baizhi.cmfz.dao;


import com.baizhi.cmfz.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {


    List<Article> queryAll();

    Integer findByCount();

    Article queryById(String id);

    void saveArticle(Article article);

    void deleteById(String id);

    void addArticle(Article article);

    List<Article> queryBypage(@Param("start") Integer start, @Param("rows") Integer rows);

    Article queryByUidAndId(@Param("id") String id, @Param("author") String author);
}
