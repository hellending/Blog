package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class addInfoController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/addInfo")
    public String addInfo(
            @RequestParam("real_name")String real_name,
            @RequestParam("email")String email,
            @RequestParam("id_card_num")String id_card_num,
            @RequestParam("country")String country,
            @RequestParam("city")String city,
            @RequestParam("state")String province,
            @RequestParam("address")String address,
            @RequestParam("sex")String sex
    ){
        System.out.println(real_name+" "+email+" "+id_card_num+" "+country+" "+province+" "+city+" "+address+" "+sex);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            userMapper.addInfo(real_name,email,id_card_num,country,province,city,address,sex, (String) request.getSession().getAttribute("userName"));
            return "index";
    }
}
