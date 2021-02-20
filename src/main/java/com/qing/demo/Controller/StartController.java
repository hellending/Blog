package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {
    @RequestMapping("/")
    public String turnToLoad(){
         return "Load";
    }
}
