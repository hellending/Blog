package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class toRegisterController {
    @RequestMapping("/register")
    public String toRegister(){
        return "register";
    }
}
