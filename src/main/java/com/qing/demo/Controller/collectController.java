package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.Mapper.CollectMapper;
import com.qing.demo.Service.Mapper.ThumbsMapper;
import com.qing.demo.Service.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
public class collectController {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/collectIncrease")
    public void collectIncrease(
            @RequestParam("address")String address
    ){
          String []s = address.split("/");
          HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
          String userName = (String) request.getSession().getAttribute("userName");
          collectMapper.insert(s[s.length-1],s[s.length-2],userName,address);
          articleMapper.collectIncrease(address);
          userMapper.increaseCollect(userName);
    }
    @RequestMapping("collectDecrease")
    public void collectDecrease(
            @RequestParam("address")String address
    ){
        String []s = address.split("/");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        collectMapper.delete(s[s.length-1],s[s.length-2],userName);
        articleMapper.collectDecrease(address);
        userMapper.decreaseCollect(userName);
    }
}
