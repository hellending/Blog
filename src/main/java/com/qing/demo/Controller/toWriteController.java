package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class toWriteController {
    @RequestMapping("/write")
    public String write(){
        return "write";
    }
}
