package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class getTopicInfoController {
       @Autowired
       UserMapper userMapper;
       @RequestMapping("/getTopicInfo")
       public List<Integer> getTopicInfoList(
               @RequestParam("userName")String userName
       ){
           User user = userMapper.findUserByName(userName);
           List<Integer> list = new ArrayList<Integer>();
           list.add(user.getArticle_num());
           list.add(user.getCollect_num());
           list.add(user.getPraise_num());
           list.add(user.getFocused_num());
           return list;
       }
}
