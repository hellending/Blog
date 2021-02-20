package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FindAllArticles {
    @Autowired
    private ArticleMapper articleMapper;
    @RequestMapping("/findAllArticles")
    public List<Article> loadAllArticle(){
        List<Article> articleList = articleMapper.findArticle();
        return articleList;
    }
}
