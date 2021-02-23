package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class toMoArticleController {
    @RequestMapping("/modifyArticle")
    public String toMoArticle(
            @RequestParam("article")String article,
            Model model
    ){
        model.addAttribute("article", article);
        return "modifyArticle";
    }
}
