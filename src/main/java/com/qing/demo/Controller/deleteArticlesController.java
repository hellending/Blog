package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        for(String i:list_address){
            articleMapper.deleteArticlesByAddress(i);
            File file = new File(i);
            file.delete();
        }
        return "";
    }
}
