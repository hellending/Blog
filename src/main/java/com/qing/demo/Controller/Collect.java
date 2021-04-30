package com.qing.demo.Controller;


import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.Mapper.CollectMapper;
import com.qing.demo.Service.Mapper.ThumbsMapper;
import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.Article;
import com.qing.demo.Service.pojo.collect;
import com.qing.demo.Service.pojo.thumbs_up;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Collect {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ThumbsMapper thumbsMapper;

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
    @RequestMapping("/collectSearch")
    public List<collect> collectSearch(
            @RequestParam("text")String text
    ){
        StringBuffer str = new StringBuffer("%");
        for(char c:text.toCharArray()){
            str.append(String.valueOf(c));
            str.append("%");
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        List<collect> list = collectMapper.getCollectArticlesByTheme(str.toString(),userName);
        return list;
    }

    @ResponseBody
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

    @ResponseBody
    @RequestMapping("/getCollectAndLikesNum")
    public int[] getCollectAndLikesNum(
            @RequestParam("address")String address
    ){
        Article article = articleMapper.getCollectAndThumbsByAddress(address);
        int []re = new int[2];
        re[0] = article.getThumbs_up();
        re[1] = article.getCollect_num();
        return re;
    }

    @ResponseBody
    @RequestMapping("ifCollectAndThumbs")
    public int[] ifCollectAndThumbs(
            @RequestParam("address")String address
    ){
        int []re = new int[2];
        String []s = address.split("/");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        collect c = collectMapper.ifCollect(s[s.length-1],s[s.length-2],userName);
        thumbs_up t = thumbsMapper.ifThumbs(s[s.length-1],s[s.length-2],userName);
        if(c==null){
            re[0] = 0;
        }
        else re[0] = 1;
        if(t==null){
            re[1] = 0;
        }
        else re[1] = 1;
        return re;
    }
}
