package com.qing.demo.Service.Mapper;

import com.qing.demo.Service.pojo.comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    public void insertComment(String reviewer,String content,String master,String article);
    public List<comment> getComment(String master, String article);
}
