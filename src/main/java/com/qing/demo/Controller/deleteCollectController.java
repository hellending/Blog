package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.Mapper.CollectMapper;
import com.qing.demo.Service.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class deleteCollectController {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/deleteCollect")
    public void deleteCollect(
            @RequestParam("list_address[]")String[] list_address
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        for (String listAddress : list_address) {
             collectMapper.deleteByAddress(listAddress);
             articleMapper.collectDecrease(listAddress);
             userMapper.decreaseCollect(userName);
        }
    }
}
