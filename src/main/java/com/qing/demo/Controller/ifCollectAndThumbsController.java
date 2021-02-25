package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.CollectMapper;
import com.qing.demo.Service.Mapper.ThumbsMapper;
import com.qing.demo.Service.pojo.collect;
import com.qing.demo.Service.pojo.thumbs_up;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ifCollectAndThumbsController {
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    ThumbsMapper thumbsMapper;
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
