package com.baizhi.cmfz;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.respository.ArticleRespository;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.service.UserService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {


    @Autowired
    private ArticleService articleService;
    @Autowired
    TransportClient transportClient;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleRespository articleRespository;

    //更新索引库

    @Test
    public void updateEs() throws IOException {
        List<Article> articles = articleService.queryAll();
        for (Article article : articles) {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
            xContentBuilder.startObject()
                    .field("id", article.getId())
                    .field("title", article.getTitle())
                    .field("author", article.getAuthor())
                    .field("content", article.getContent())
                    .field("status", article.getStatus())
                    .field("publishTime", article.getPublishTime())
                    .endObject();
            transportClient.prepareIndex("cmfz", "article").setSource(xContentBuilder).get();
        }
    }

    @Test
    public void test1() {
        Integer count = userDao.queryByTime(7, "女");
        System.out.println("count = " + count);
    }


    @Test
    public void testMessage() {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou",
                "<accessKeyId>",
                "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "17698385160");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void save(){
        List<Article> articles = articleService.queryAll();
        for (Article article : articles) {
            Article article1 = new Article();
            article1.setId(article.getId());
            article1.setAuthor(article.getAuthor());
            article1.setContent(article.getContent());
            article1.setStatus(article.getStatus());
            article1.setPublishTime(article.getPublishTime());
            article1.setTitle(article.getTitle());
            articleRespository.save(article1);
        }
    }
}

