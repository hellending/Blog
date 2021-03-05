package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.FocusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class getFocusController {
    @Autowired
    FocusMapper focusMapper;
    @RequestMapping("/getFocus")
    public String[] getFocus() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String []s = focusMapper.getFocus1(userName);
        return s;
    }
}
