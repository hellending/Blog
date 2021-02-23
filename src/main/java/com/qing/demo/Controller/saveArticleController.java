package com.qing.demo.Controller;

import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class saveArticleController {
    @RequestMapping("/saveArticle")
    public String saveArticle(@RequestParam("article")String html,
                              @RequestParam("theme")String theme,
                              @RequestParam("model")String article
                              ) throws IOException {
        System.out.println(theme);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article"+"/"+userName+"/"+theme+".txt";
        BufferedWriter out = new BufferedWriter(new FileWriter(path));
        out.write(html);
        out.close();
        path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article_model/"+userName+"/"+theme+".txt";
        out = new BufferedWriter(new FileWriter(path));
        out.write(article);
        out.close();
        return "";
    }
}
