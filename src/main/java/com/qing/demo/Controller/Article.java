package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.Mapper.CollectMapper;
import com.qing.demo.Service.Mapper.ThumbsMapper;
import com.qing.demo.Service.Mapper.UserMapper;
import com.qing.demo.Service.pojo.collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
public class Article {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    ThumbsMapper thumbsMapper;

    @ResponseBody
    @RequestMapping("/allArticlesSearch")
    public List<com.qing.demo.Service.pojo.Article> allArticles(
            @RequestParam("text")String text
    ){
        StringBuffer str = new StringBuffer("%");
        for(char c:text.toCharArray()){
            str.append(String.valueOf(c));
            str.append("%");
        }
        return articleMapper.findAllArticleByTheme(str.toString());
    }

    @ResponseBody
    @RequestMapping("/articleRepeat")
    public String articleRepeat(
            @RequestParam("theme")String theme
    ){
        System.out.println("qqqq");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String s = articleMapper.findAddress(userName,theme);
        if(s==null)
            return "true";
        else return "false";
    }

    @ResponseBody
    @RequestMapping("/deleteArticles")
    public String deleteArticles(
            @RequestParam("list_address[]")String[] list_address
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article_model/"+userName+"/";
        for(String i:list_address){
            articleMapper.deleteArticlesByAddress(i);
            userMapper.deleteArticle(userName);
            List<String> collector = collectMapper.getCollector(i);
            for(String s:collector){
                userMapper.decreaseCollect(s);
            }
            collectMapper.deleteByAddress(i);
            String []ss = i.split("/");
            String f_name = ss[ss.length-1].substring(0,ss[ss.length-1].length()-4);
            String master = ss[ss.length-2];
            thumbsMapper.deleteByArticle(master,f_name);
            File file = new File(i);
            System.gc();
            file.delete();
            String fileName = i.substring(i.lastIndexOf("/")+1);
            String path1 = path+fileName;
            System.out.println(path1);
            File file1 = new File(path1);
            file1.delete();
        }
        return "";
    }

    @ResponseBody
    @RequestMapping("/findAllArticles")
    public List<com.qing.demo.Service.pojo.Article> loadAllArticle(){
        List<com.qing.demo.Service.pojo.Article> articleList = articleMapper.findArticle();
        return articleList;
    }

    @ResponseBody
    @RequestMapping("/getArticles")
    public List<com.qing.demo.Service.pojo.Article> getArticles(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        List<com.qing.demo.Service.pojo.Article> articlesList = articleMapper.findArticleByMaster(userName);
        return articlesList;
    }

    @ResponseBody
    @RequestMapping("/getCollectArticles")
    public List<collect> getCollectArticles(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        List<collect> list = collectMapper.getCollectArticles(userName);
        return list;
    }

    @ResponseBody
    @RequestMapping("/getOthersArticles")
    public List<com.qing.demo.Service.pojo.Article> getOthersArticle(
            @RequestParam("userName")String userName
    ){
        List<com.qing.demo.Service.pojo.Article> list = articleMapper.findArticleByMaster(userName);
        return list;
    }

    @ResponseBody
    @RequestMapping("insertArticle")
    public String insertArticle(
            @RequestParam("theme")String theme
    ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String address = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article/"+userName+"/"+theme+".txt";
        articleMapper.insertArticle(userName,theme,address);
        userMapper.writeArticle(userName);
        return "";
    }

    @ResponseBody
    @RequestMapping("/loadArticle")
    public String loadArticle(
            @RequestParam("address") String address
    ) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(address));
        String str;
        StringBuffer re = new StringBuffer();
        while ((str = in.readLine()) != null) {
            re.append(str+"\n");
        }
        return re.toString();
    }

    @ResponseBody
    @RequestMapping("/personalSearch")
    public List<com.qing.demo.Service.pojo.Article> personalSearch(
            @RequestParam("text")String text
    ){
        StringBuffer str = new StringBuffer("%");
        for(char c:text.toCharArray()){
            str.append(String.valueOf(c));
            str.append("%");
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        List<com.qing.demo.Service.pojo.Article> articlesList = articleMapper.findArticleByTheme(str.toString(),userName);
        return articlesList;
    }

    @ResponseBody
    @RequestMapping("/saveArticle")
    public String saveArticle(@RequestParam("article")String html,
                              @RequestParam("theme")String theme,
                              @RequestParam("model")String article
    ) throws IOException {
        System.out.println(theme);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("userName");
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article"+"/"+userName+"/"+theme+".txt";
        BufferedWriter out = new BufferedWriter(new FileWriter(path));
        out.write(html);
        out.close();
        path = ClassUtils.getDefaultClassLoader().getResource("static").getPath()+"/article_model/"+userName+"/"+theme+".txt";
        out = new BufferedWriter(new FileWriter(path));
        out.write(article);
        out.close();
        return "";
    }


}
