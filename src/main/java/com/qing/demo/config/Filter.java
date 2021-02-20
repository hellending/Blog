package com.qing.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Filter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userName = (String) request.getSession().getAttribute("userName");
        if(userName != null)return true;
        else {
            request.getRequestDispatcher("/").forward(request,response);
        }
        return false;
    }
}
