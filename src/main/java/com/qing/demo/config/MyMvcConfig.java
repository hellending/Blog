package com.qing.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Filter()).addPathPatterns("/**").excludePathPatterns("/","/register_handle","/load_handle","/css/*.css"
                ,"/js/*.js","/img/*.*","/register","/if_repeat","/test","/test1");
    }
}
