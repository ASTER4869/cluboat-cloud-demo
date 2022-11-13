package com.cluboat.springcloud.entities;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class user
{
    private String name;

    private String stu_id;

    private String phone;

    private long id;



}
