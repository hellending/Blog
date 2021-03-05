package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.Mapper.ThumbsMapper;
import com.qing.demo.Service.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class thumbsController {
      @Autowired
      ArticleMapper articleMapper;
      @Autowired
      ThumbsMapper thumbsMapper;
      @Autowired
      UserMapper userMapper;
      @RequestMapping("/thumbsIncrease")
      public void thumbsIncrease(
           @RequestParam("address")String address
      ){
          String []s = address.split("/");
          HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
          String userName = (String) request.getSession().getAttribute("userName");
          thumbsMapper.insert(s[s.length-1],s[s.length-2],userName);
          articleMapper.thumbsIncrease(address);
          userMapper.increaseThumbs(s[s.length-2]);
      }
      @RequestMapping("/thumbsDecrease")
      public void thumbsDecrease(
              @RequestParam("address")String address
      ){
          String []s = address.split("/");
          HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
          String userName = (String) request.getSession().getAttribute("userName");
          thumbsMapper.delete(s[s.length-1],s[s.length-2],userName);
          articleMapper.thumbsDecrease(address);
          userMapper.decreaseThumbs(s[s.length-2]);
      }
}
