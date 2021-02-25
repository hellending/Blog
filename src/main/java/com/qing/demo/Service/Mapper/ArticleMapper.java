package com.qing.demo.Service.Mapper;

import com.qing.demo.Service.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Mapper
public interface ArticleMapper {
    public void insertArticle(String userName,String theme,String address);
    public String findAddress(String userName,String theme);
    public List<Article> findArticleByMaster(String userName);
    public List<Article> findArticleByTheme(String themeLike,String userName);
    public List<Article> findArticle();
    public List<Article> findAllArticleByTheme(String themeLike);
    public void deleteArticlesByAddress(String address);
    public void collectIncrease(String address);
    public void thumbsIncrease(String address);
    public void collectDecrease(String address);
    public void thumbsDecrease(String address);
    public Article getCollectAndThumbsByAddress(String address);
}
