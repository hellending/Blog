package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
