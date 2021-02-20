package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class toTest {
    @RequestMapping("/test")
    public String Test(){
        return "test";
    }
}
