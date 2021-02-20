package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class loadController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/load_handle")
    public String load(@RequestParam("userName")String userName,@RequestParam("password") String password,Model model,
                       HttpSession session){
        List<User> users = userMapper.selectUser();
        int flag = 0;
        for(User user:users){
            if(user.getUserName().equals(userName)&&user.getPassword().equals(password)){
                model.addAttribute("userName",userName);
                session.setAttribute("userName",userName);
                return "index";
            }
            else if(user.getUserName().equals(userName)&&!user.getPassword().equals(password)){
                flag = 1;
                break;
            }
        }
        if(flag==1)
           model.addAttribute("msg","密码有误");
        else
           model.addAttribute("msg","用户名不存在");
        return "Load";
    }
}
