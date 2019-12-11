package com.baizhi.cmfz.respository;

import com.baizhi.cmfz.entity.Article;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ArticleRespository extends ElasticsearchRepository<Article,String>{

   /*
    以下是通过Spring对es的操作
    @Autowired
    TransportClient transportClient;

    public List<Map> search(String query, Integer from) {

        List<Map> list = new ArrayList<>();

        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(query)
                .field("title")
                .field("author")
                .field("content")
                .field("status");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.requireFieldMatch(false)
                .preTags("<font color='red'>")
                .postTags("</font>")
                .field("*");

        SearchResponse response = transportClient.prepareSearch("cmfz")
                .setTypes("article")
                .setQuery(queryStringQueryBuilder)
                .highlighter(highlightBuilder)
                .setFrom(from)
                .setSize(4)
                .get();

        *//*
         * 如果该字段有高亮则返回高亮
         * 如果没有返回原始数据
         * *//*
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            for (String s : sourceAsMap.keySet()) {
                if (highlightFields.get(s) != null) {
                    sourceAsMap.put(s, highlightFields.get(s).getFragments()[0].toString());
                }
            }
            list.add(sourceAsMap);
        }
        return list;
    }*/
}