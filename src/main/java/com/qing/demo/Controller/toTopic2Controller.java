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
public class toTopic2Controller {
    @RequestMapping("/toTopic2")
    public String toTopic(
            @RequestParam("master")String master,
            Model model,
            HttpSession session
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        model.addAttribute("userName",master);
        session.setAttribute("userName",userName);
        return "topic";
    }
}
