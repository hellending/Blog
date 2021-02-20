package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class allArticlesSearchController {
    @Autowired
    private ArticleMapper articleMapper;
    @RequestMapping("/allArticlesSearch")
    public List<Article> allArticles(
            @RequestParam("text")String text
    ){
        StringBuffer str = new StringBuffer("%");
        for(char c:text.toCharArray()){
            str.append(String.valueOf(c));
            str.append("%");
        }
        return articleMapper.findAllArticleByTheme(str.toString());
    }
}
