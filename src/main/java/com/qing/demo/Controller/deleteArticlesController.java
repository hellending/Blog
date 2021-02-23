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
import java.io.File;
import java.util.*;

@RestController
public class deleteArticlesController {
    @Autowired
    private ArticleMapper articleMapper;
    @RequestMapping("/deleteArticles")
    public String deleteArticles(
            @RequestParam("list_address[]")String[] list_address
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article_model/"+userName+"/";
        for(String i:list_address){
            articleMapper.deleteArticlesByAddress(i);
            File file = new File(i);
            System.gc();
            file.delete();
            String fileName = i.substring(i.lastIndexOf("/")+1);
            String path1 = path+fileName;
            System.out.println(path1);
            File file1 = new File(path1);
            file1.delete();
        }
        return "";
    }
}
