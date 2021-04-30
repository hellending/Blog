package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.CommentMapper;
import com.qing.demo.Service.pojo.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Comment {
    @Autowired
    CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping("/saveComments")
    public void saveComments(
            @RequestParam("content")String content,
            @RequestParam("master")String master,
            @RequestParam("article")String article
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        commentMapper.insertComment(userName,content,master,article);
    }

    @ResponseBody
    @RequestMapping("/getComments")
    public List<List<String>>getComments(
            @RequestParam("master")String master,
            @RequestParam("article")String article
    ){
        List<List<String>> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<comment> list_comment = commentMapper.getComment(master,article);
        for(comment c:list_comment){
            list1.add(c.getReviewer());
            list2.add(c.getContent());
        }
        list.add(list1);
        list.add(list2);
        return list;
    }
}
