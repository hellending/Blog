package com.qing.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class toModifyController {
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
}
