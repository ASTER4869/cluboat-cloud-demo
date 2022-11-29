package com.cluboat.springcloud.entity.po;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfo {
    @TableId
    private Integer user_id;

    private String user_name;

    private String user_phone;

    private String user_sexual;
    private Timestamp user_create_time;
    private String user_photoURL;
    private String user_sign;
}
