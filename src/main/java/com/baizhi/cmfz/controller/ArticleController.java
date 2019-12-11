package com.baizhi.cmfz.controller;

import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDao articleDao;

    /**
     * 分页满足四个条件
     * <p>
     * total：总页数
     * page：当前页
     * records总条数
     * rows:集合
     */

    @RequestMapping("queryByPage")
    @ResponseBody
    public Map<String, Object> queryByPage(Integer page, Integer rows) {

        Map<String, Object> map = articleService.queryByPage(page, rows);
        return map;
    }


    @RequestMapping("/add")
    @ResponseBody
    public void add(Article article) {
        articleService.add(article);
    }

   @RequestMapping("queryById")
   @ResponseBody
   public Article queryById(String id){

       Article article = articleService.findById(id);
       return article;
   }



    @RequestMapping("/edit")
    public void edit(String oper, String id,Article article) {
        if (oper.equals("del")) {
            System.out.println("删除" + id);
            articleService.deleteById(id);
        }if(oper.equals("edit")){
            articleService.saveArticle(article);
        }
    }


    @RequestMapping("queryByEs")
    @ResponseBody
    public List<Article> queryByEs(String value){

        System.out.println("value = " + value);

        List<Article> articles = articleService.querByHigh(value);

        return articles;
    }








    /*@RequestMapping("/search")
    @ResponseBody
    public List<Map> search(String query, Integer pageIndex) {
        return articleService.search(query, pageIndex);
    }*/

    /*//参数：文章id，用户id
    @RequestMapping("si")
    @ResponseBody
    public Map<String, Object> si(String id, String author) {

        Map<String, Object> map = new HashMap<>();
        *//**
         *  "code": 200,
         "   link": "http://xxx/1000.html", #跳转地址（HTML页面，客户端通过webView方式打开）
         "   id": "100024",                 #文章id
         *//*
        Article article = articleDao.queryByUidAndId(id, author);
        try {
            map.put("code", 200);
            map.put("link", article.getContent());
            map.put("id", article.getId());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "参数错误");
        }
        return map;
    }
*/

}
