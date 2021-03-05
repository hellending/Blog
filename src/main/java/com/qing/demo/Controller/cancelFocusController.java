package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.FocusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class cancelFocusController {
    @Autowired
    FocusMapper focusMapper;
    @RequestMapping("/cancelFocus")
    public void cancelFocus(
            @RequestParam("focus_who")String focus_who
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        focusMapper.focusDecrease(focus_who,userName);
        return;
    }
}
