package com.qing.demo.Service.Mapper;

import com.qing.demo.Service.pojo.Focus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FocusMapper {
    public Focus getFocus(String focus_who,String follower);
    public void focusIncrease(String focus_who,String follower);
    public void focusDecrease(String focus_who,String follower);
    public String[] getFocus1(String follower);
}
