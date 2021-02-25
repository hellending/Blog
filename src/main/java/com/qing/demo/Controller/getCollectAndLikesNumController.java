package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class getCollectAndLikesNumController {
       @Autowired
       ArticleMapper articleMapper;
       @RequestMapping("/getCollectAndLikesNum")
       public int[] getCollectAndLikesNum(
               @RequestParam("address")String address
       ){
            Article article = articleMapper.getCollectAndThumbsByAddress(address);
            int []re = new int[2];
            re[0] = article.getThumbs_up();
            re[1] = article.getCollect_num();
            return re;
       }
}
