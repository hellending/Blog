package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class User {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/addInfo")
    public String addInfo(
            @RequestParam("real_name")String real_name,
            @RequestParam("email")String email,
            @RequestParam("id_card_num")String id_card_num,
            @RequestParam("country")String country,
            @RequestParam("city")String city,
            @RequestParam("state")String province,
            @RequestParam("address")String address,
            @RequestParam("sex")String sex
    ){
        System.out.println(real_name+" "+email+" "+id_card_num+" "+country+" "+province+" "+city+" "+address+" "+sex);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        userMapper.addInfo(real_name,email,id_card_num,country,province,city,address,sex, (String) request.getSession().getAttribute("userName"));
        return "index";
    }

    @ResponseBody
    @RequestMapping("changeHead")
    public String changeHead(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/headPortrait/"+userName+"/userLogo.jpg";
        System.gc();
        file.transferTo(new File(path));
        return "";
    }

    @ResponseBody
    @RequestMapping("/getTopicInfo")
    public List<Integer> getTopicInfoList(
            @RequestParam("userName")String userName
    ){
        com.qing.demo.Service.pojo.User user = userMapper.findUserByName(userName);
        List<Integer> list = new ArrayList<Integer>();
        list.add(user.getArticle_num());
        list.add(user.getCollect_num());
        list.add(user.getPraise_num());
        list.add(user.getFocused_num());
        return list;
    }

    @RequestMapping("/modify")
    public String modify() {
        return "/info";
    }

    @ResponseBody
    @RequestMapping("/if_repeat")
    public String ifReatPeat(@RequestParam("name")String userName){
        try {
            com.qing.demo.Service.pojo.User user = userMapper.findUserByName(userName);
            if(user!=null)
                return "用户名已存在";
            else return "";
        }
        catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("/register_handle")
    public String register_controller(@RequestParam("userName")String userName,
                                      @RequestParam("password")String password,
                                      @RequestParam("telNum")String telNum) throws IOException {
        System.out.println("1111");
        userMapper.insertUser(userName,password,telNum);
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article";
        path+=("/"+userName);
        File file = new File(path);
        file.mkdir();
        path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/picture";
        path+=("/"+userName);
        File file1 = new File(path);
        file1.mkdir();
        path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/headPortrait";
        path+=("/"+userName);
        File file2 = new File(path);
        file2.mkdir();
        String path1 = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/img/"+"userLogo.jpg";
        File originalFile = new File(path1);
        String savePath = path+"/userLogo.jpg";
        File result = new File(savePath);
        FileInputStream in = new FileInputStream(originalFile);
        FileOutputStream out = new FileOutputStream(result);// 指定要写入的图片
        int n = 0;// 每次读取的字节长度
        byte[] bb = new byte[1024];// 存储每次读取的内容
        while ((n = in.read(bb)) != -1) {
            out.write(bb, 0, n);// 将读取的内容，写入到输出流当中
        }
        path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article_model/"+userName;
        File file3 = new File(path);
        file3.mkdir();
        return "Load";
    }
}
