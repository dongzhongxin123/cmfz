package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.respository.ArticleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleRespository articleRespository;


    @Override
    public List<Article> queryAll() {
        return articleDao.queryAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer findByCount() {
        return articleDao.findByCount();
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Article findById(String id) {
        Article article = articleDao.queryById(id);
        return article;
    }


    @Override
    public void saveArticle(Article article) {
        articleDao.saveArticle(article);
    }

    @Override
    public void deleteById(String id) {
        articleDao.deleteById(id);
    }

    @Override
    public void add(Article article) {
        article.setId(UUID.randomUUID().toString());
        articleDao.addArticle(article);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer start = (page - 1) * rows;
        List<Article> articles = articleDao.queryBypage(start, rows);
        Integer count = articleDao.findByCount();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("rows", articles);
        map.put("total", total);
        map.put("page", page);
        map.put("records", count);

        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Map> search(String query, Integer pageIndex) {
        Integer from = (pageIndex - 1) * 4;
        List<Map> search = articleRespository.search(query, from);
        return search;
    }
}
