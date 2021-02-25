package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class getOthersArticlesController {
     @Autowired
     ArticleMapper articleMapper;
     @RequestMapping("/getOthersArticles")
     public List<Article> getOthersArticle(
             @RequestParam("userName")String userName
     ){
             List<Article> list = articleMapper.findArticleByMaster(userName);
             return list;
     }
}
