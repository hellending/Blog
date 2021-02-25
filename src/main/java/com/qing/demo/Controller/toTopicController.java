package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class toTopicController {
    @RequestMapping("/toTopic")
    public String toTopic(
            @RequestParam("master")String master,
            @RequestParam("userName")String userName,
            Model model,
            HttpSession session
    ){
        model.addAttribute("userName",master);
        session.setAttribute("userName",userName);
        return "topic";
    }
}
