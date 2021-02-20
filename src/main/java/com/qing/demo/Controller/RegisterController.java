package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;

@Controller
public class RegisterController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/register_handle")
    public String register_controller(@RequestParam("userName")String userName,
                                      @RequestParam("password")String password,
                                      @RequestParam("telNum")String telNum) throws IOException {
          System.out.println("1111");
          userMapper.insertUser(userName,password,telNum);
          String path = "F:\\IdeaProjects\\blog\\src\\main\\resources\\static\\article";
          path+=("\\"+userName);
          File file = new File(path);
          file.mkdir();
          return "Load";
    }
}
