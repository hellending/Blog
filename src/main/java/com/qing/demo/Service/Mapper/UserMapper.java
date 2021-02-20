package com.qing.demo.Service.Mapper;

import com.qing.demo.Service.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Mapper
public interface UserMapper {
    List<User> selectUser();
    void insertUser(String name,String password,String telNum);
    User findUserByName(String name);
    void addInfo(String real_name,String email,String id_card_num,String country,String province,String city,String address,String sex,String name);
}
