package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class toIfoController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/info")
    public ModelAndView goTo(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        User user = userMapper.findUserByName((String) request.getSession().getAttribute("userName"));
        ModelAndView modelAndView = new ModelAndView();
        if(user.getReal_name()==null) {
            modelAndView.setViewName("info");
        }
        else {
            modelAndView.setViewName("pre_info");
        }
        modelAndView.addObject("userName",user.getUserName());
        modelAndView.addObject("password",user.getPassword());
        modelAndView.addObject("telephoneNum",user.getTelephoneNum());
        modelAndView.addObject("sex",user.getSex());
        modelAndView.addObject("country", user.getCountry());
        modelAndView.addObject("province",user.getProvince());
        modelAndView.addObject("city",user.getCity());
        modelAndView.addObject("email",user.getEmail());
        modelAndView.addObject("address",user.getAddress());
        modelAndView.addObject("id_card_num",user.getId_card_num());
        modelAndView.addObject("real_name",user.getReal_name());
        return modelAndView;
    }
}
