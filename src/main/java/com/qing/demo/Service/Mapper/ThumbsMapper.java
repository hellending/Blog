package com.qing.demo.Service.Mapper;

import com.qing.demo.Service.pojo.thumbs_up;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ThumbsMapper {
    public void insert(String article_name,String master,String who);
    public void delete(String article_name,String master,String who);
    public thumbs_up ifThumbs(String article_name,String master,String who);
    public void deleteByArticle(String master,String article_name);
}
