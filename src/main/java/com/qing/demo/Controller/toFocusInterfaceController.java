package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class toFocusInterfaceController {
    @RequestMapping("/toFocusInterface")
    public String toFocusInterface(){
        return "focus";
    }
}
