package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.FocusMapper;
import com.qing.demo.Service.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Focus {
    @Autowired
    FocusMapper focusMapper;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/cancelFocus")
    @ResponseBody
    public void cancelFocus(
            @RequestParam("focus_who")String focus_who
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        focusMapper.focusDecrease(focus_who,userName);
        return;
    }

    @ResponseBody
    @RequestMapping("/ifFocus")
    public boolean ifFocus(
            @RequestParam("userName")String userName,
            @RequestParam("focus_who")String focus_who
    ){
        com.qing.demo.Service.pojo.Focus focus = focusMapper.getFocus(focus_who,userName);
        System.out.println(focus_who);
        if(focus==null)
            return true;
        return false;
    }

    @ResponseBody
    @RequestMapping("focusIncrease")
    public void focusIncrease(
            @RequestParam("userName")String userName,
            @RequestParam("focus_who")String focus_who
    ){
        focusMapper.focusIncrease(focus_who,userName);
        userMapper.increaseFocus(focus_who);
    }

    @ResponseBody
    @RequestMapping("/focusDecrease")
    public void focusDecrease(
            @RequestParam("userName")String userName,
            @RequestParam("focus_who")String focus_who
    ){
        focusMapper.focusDecrease(focus_who,userName);
        userMapper.decreaseFocus(focus_who);
    }

    @ResponseBody
    @RequestMapping("/getFocus")
    public String[] getFocus() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String []s = focusMapper.getFocus1(userName);
        return s;
    }
}
