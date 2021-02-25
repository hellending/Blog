package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class toCollectArticlesController {
    @RequestMapping("/toCollection")
    public String toCollection(){
        return "Collection";
    }
}
