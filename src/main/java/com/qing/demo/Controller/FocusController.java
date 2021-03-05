package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.FocusMapper;
import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.Focus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FocusController {
    @Autowired
    FocusMapper focusMapper;
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/ifFocus")
    public boolean ifFocus(
            @RequestParam("userName")String userName,
            @RequestParam("focus_who")String focus_who
    ){
        Focus focus = focusMapper.getFocus(focus_who,userName);
        System.out.println(focus_who);
        if(focus==null)
           return true;
        return false;
    }
    @RequestMapping("focusIncrease")
    public void focusIncrease(
            @RequestParam("userName")String userName,
            @RequestParam("focus_who")String focus_who
    ){
           focusMapper.focusIncrease(focus_who,userName);
           userMapper.increaseFocus(focus_who);
    }
    @RequestMapping("/focusDecrease")
    public void focusDecrease(
            @RequestParam("userName")String userName,
            @RequestParam("focus_who")String focus_who
    ){
           focusMapper.focusDecrease(focus_who,userName);
           userMapper.decreaseFocus(focus_who);
    }
}
