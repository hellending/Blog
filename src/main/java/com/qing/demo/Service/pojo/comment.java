package com.qing.demo.Service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class comment {
    String master;
    String article;
    String reviewer;
    String content;
}
