package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.CollectMapper;
import com.qing.demo.Service.pojo.collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class getCollectArticlesController {
    @Autowired
    CollectMapper collectMapper;
    @RequestMapping("/getCollectArticles")
    public List<collect> getCollectArticles(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        List<collect> list = collectMapper.getCollectArticles(userName);
        return list;
    }
}
