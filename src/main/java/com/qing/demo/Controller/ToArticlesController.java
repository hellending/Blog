package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToArticlesController {
    @RequestMapping("/toArticles")
    public String toArticles(){
        return "articles";
    }
}
