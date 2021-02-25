package com.qing.demo.Controller;

import com.qing.demo.Service.Mapper.ArticleMapper;
import com.qing.demo.Service.Mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class deleteCollectController {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CollectMapper collectMapper;
    @RequestMapping("/deleteCollect")
    public void deleteCollect(
            @RequestParam("list_address[]")String[] list_address
    ){
        for (String listAddress : list_address) {
             collectMapper.deleteByAddress(listAddress);
             articleMapper.collectDecrease(listAddress);
        }
    }
}
