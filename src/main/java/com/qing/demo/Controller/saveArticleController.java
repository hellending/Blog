package com.qing.demo.Controller;

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
                              @RequestParam("theme")String theme) throws IOException {
        System.out.println("111111");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String UserName = (String) request.getSession().getAttribute("userName");
        String path = "F:\\IdeaProjects\\blog\\src\\main\\resources\\static\\article"+"\\"+userName+"\\"+theme+".txt";
        BufferedWriter out = new BufferedWriter(new FileWriter(path));
        out.write(html);
        out.close();
        return "";
    }
}
