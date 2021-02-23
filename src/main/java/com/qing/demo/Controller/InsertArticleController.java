package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class InsertArticleController {
    @Autowired
    private ArticleMapper articleMapper;
    @RequestMapping("insertArticle")
    public String insertArticle(
            @RequestParam("theme")String theme
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String address = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article/"+userName+"/"+theme+".txt";
        articleMapper.insertArticle(userName,theme,address);
        return "";
    }
}
