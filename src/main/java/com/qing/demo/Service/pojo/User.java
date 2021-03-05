package com.qing.demo.Service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    String userName;
    String password;
    String telephoneNum;
    String sex;
    String country;
    String email;
    String province;
    String city;
    String address;
    String id_card_num;
    String real_name;
    int praise_num;
    int collect_num;
    int article_num;
    int focused_num;
}
