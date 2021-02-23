package com.qing.demo.Controller;

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

@RestController
public class changeHeadController {
    @RequestMapping("changeHead")
    public String changeHead(
            @RequestParam("file")MultipartFile file
            ) throws IOException {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String userName = (String) request.getSession().getAttribute("userName");
            String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/headPortrait/"+userName+"/userLogo.jpg";
            System.gc();
            file.transferTo(new File(path));
            return "";
         }
}
