package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.respository.ArticleRespository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


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
    public List<Article> querByHigh(String key) {
        HighlightBuilder.Field highField=new HighlightBuilder.Field("*");
        highField.preTags("<span style='color:red'>");
        highField.postTags("</span>");
        highField.requireFieldMatch(false);



        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withIndices("cmfz")
                .withTypes("article")
                .withQuery(QueryBuilders.multiMatchQuery(key,"title","author","content","status"))
                .withHighlightFields(highField)
                .build();


        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(build, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                SearchHits hits = searchResponse.getHits();

                SearchHit[] hits1 = hits.getHits();

                ArrayList<Object> list = new ArrayList<>();

                for (SearchHit searchHitFields : hits1) {

                    Map<String, Object> map = searchHitFields.getSourceAsMap();

                    Article article = new Article();
                    article.setId(map.get("id").toString());
                    article.setTitle(map.get("title").toString());
                    article.setPublishTime(new Date(Long.valueOf(map.get("publishTime").toString())));
                    article.setContent(map.get("content").toString());
                    article.setAuthor(map.get("author").toString());
                    article.setStatus(map.get("status").toString());

                    //处理高亮
                    Map<String, HighlightField> highlightFields = searchHitFields.getHighlightFields();
                    if(highlightFields.get("title")!=null){
                        article.setTitle(highlightFields.get("title").getFragments()[0].string());
                    }

                    if(highlightFields.get("status")!=null){
                        article.setStatus(highlightFields.get("status").getFragments()[0].string());
                    }

                    if(highlightFields.get("content")!=null){
                        article.setContent(highlightFields.get("content").getFragments()[0].string());
                    }

                    if(highlightFields.get("author")!=null){
                        article.setAuthor(highlightFields.get("author").getFragments()[0].string());
                    }

                    list.add(article);
                }
                return new AggregatedPageImpl<T>((List<T>)list);
            }
        });
        return articles.getContent();
    }







   /* @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Map> search(String query, Integer pageIndex) {
        Integer from = (pageIndex - 1) * 4;
        List<Map> search = articleRespository.search(query, from);
        return search;
    }*/
}
