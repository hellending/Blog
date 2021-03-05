package com.qing.demo.Service.Mapper;

import com.qing.demo.Service.pojo.collect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface CollectMapper {
     public void insert(String article_name,String master,String collector,String address);
     public void delete(String article_name,String master,String collector);
     public collect ifCollect(String article_name,String master,String collector);
     public List<collect> getCollectArticles(String collector);
     public List<collect> getCollectArticlesByTheme(String themeLike,String collector);
     public void deleteByAddress(String address);
     public List<String> getCollector(String address);
}
