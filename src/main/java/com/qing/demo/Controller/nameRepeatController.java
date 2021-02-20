package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class nameRepeatController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/if_repeat")
    public String ifReatPeat(@RequestParam("name")String userName){
        try {
            User user = userMapper.findUserByName(userName);
            if(user!=null)
                return "用户名已存在";
            else return "";
        }
        catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
