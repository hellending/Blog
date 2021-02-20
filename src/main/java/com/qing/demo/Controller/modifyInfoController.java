package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class modifyInfoController {
    @RequestMapping("/modify")
    public String modify() {
        return "/info";
    }
}
