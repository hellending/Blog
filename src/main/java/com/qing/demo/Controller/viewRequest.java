package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Controller
public class viewRequest {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/load_handle")
    public String load(@RequestParam("userName")String userName, @RequestParam("password") String password, Model model,
                       HttpSession session){
        List<com.qing.demo.Service.pojo.User> users = userMapper.selectUser();
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

    @RequestMapping("/home")
    public String toHome(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        model.addAttribute("userName",request.getSession().getAttribute("userName"));
        return "index";
    }

    @RequestMapping("/")
    public String turnToLoad(){
        return "Load";
    }

    @RequestMapping("/toArticles")
    public String toArticles(){
        return "articles";
    }

    @RequestMapping("/toCollection")
    public String toCollection(){
        return "Collection";
    }

    @RequestMapping("/toFocusInterface")
    public String toFocusInterface(){
        return "focus";
    }

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

    @RequestMapping("/toModify")
    public String toModify(
            @RequestParam("address") String address,
            Model model
    ) throws IOException {
        String fileName = address.substring(address.lastIndexOf("/")+1);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article_model/"+userName+"/"+fileName;
        System.out.println(address);
        BufferedReader in = new BufferedReader(new FileReader(path));
        String str;
        StringBuffer re = new StringBuffer();
        while ((str = in.readLine()) != null) {
            re.append(str+"\n");
        }
        model.addAttribute("article",re.toString());
        model.addAttribute("fileName",fileName);
        return "modifyArticle";
    }

    @RequestMapping("/toModifyCount")
    public String toModifyCount(){
        return "modifyCount";
    }

    @RequestMapping("/register")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/toTopic2")
    public String toTopic(
            @RequestParam("master")String master,
            Model model,
            HttpSession session
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        model.addAttribute("userName",master);
        session.setAttribute("userName",userName);
        return "topic";
    }

    @RequestMapping("/toTopic")
    public String toTopic(
            @RequestParam("master")String master,
            @RequestParam("userName")String userName,
            Model model,
            HttpSession session
    ){
        model.addAttribute("userName",master);
        session.setAttribute("userName",userName);
        return "topic";
    }

    @RequestMapping("/write")
    public String write(){
        return "write";
    }

    @RequestMapping("/test")
    public String Test(){
        return "test";
    }

    @ResponseBody
    @RequestMapping("/test1")
    public String test(){
        return "hhhhh";
    }
}
