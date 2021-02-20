package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class loadArticleController {
    @RequestMapping("/loadArticle")
    public String loadArticle(
            @RequestParam("address") String address
    ) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(address));
        String str;
        StringBuffer re = new StringBuffer();
        while ((str = in.readLine()) != null) {
            re.append(str);
        }
        return re.toString();
    }
}
