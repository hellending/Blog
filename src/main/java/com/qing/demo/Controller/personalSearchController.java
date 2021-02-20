package com.qing.demo.Controller;


import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class personalSearchController {
    @Autowired
    private ArticleMapper articleMapper;
    @RequestMapping("/personalSearch")
    public List<Article> personalSearch(
            @RequestParam("text")String text
    ){
         StringBuffer str = new StringBuffer("%");
         for(char c:text.toCharArray()){
             str.append(String.valueOf(c));
             str.append("%");
         }
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         String userName = (String) request.getSession().getAttribute("userName");
         List<Article> articlesList = articleMapper.findArticleByTheme(str.toString(),userName);
         return articlesList;
    }
}
