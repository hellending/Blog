package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class getArticlesController {
    @Autowired
    private ArticleMapper articleMapper;
    @RequestMapping("/getArticles")
    public List<Article> getArticles(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        List<Article> articlesList = articleMapper.findArticleByMaster(userName);
        return articlesList;
    }
}
