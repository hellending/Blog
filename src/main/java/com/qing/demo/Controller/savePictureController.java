package com.qing.demo.Controller;

import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class savePictureController {
    public String getRandomName(String fileName){
        int index=fileName.lastIndexOf(".");
        String suffix=fileName.substring(index);//获取后缀名
        String uuidFileName= UUID.randomUUID().toString().replace("-","")+suffix;
        return uuidFileName;
    }
        @RequestMapping("/toPictureSave")
        public String[] savePicture(
                @RequestParam("file") MultipartFile uploadImage,
                @RequestParam("fileName") String fileName
        ) throws IOException {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String userName = (String) request.getSession().getAttribute("userName");
            String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/picture";
            fileName = getRandomName(fileName);
            path+=("/")+userName+"/"+fileName;
            System.out.println(path);
            uploadImage.transferTo(new File(path));
            String []re = new String[2];
            re[0] = userName;
            re[1] = fileName;
            return re;
        }
}
