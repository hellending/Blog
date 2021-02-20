package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class articleRepeatController {
    @Autowired
    private ArticleMapper articleMapper;
     @RequestMapping("/articleRepeat")
     public String articleRepeat(
             @RequestParam("theme")String theme
     ){
         System.out.println("qqqq");
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         String userName = (String) request.getSession().getAttribute("userName");
         String s = articleMapper.findAddress(userName,theme);
         if(s==null)
           return "true";
         else return "false";
     }
}
