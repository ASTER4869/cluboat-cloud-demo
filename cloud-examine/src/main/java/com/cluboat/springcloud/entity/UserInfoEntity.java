package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@TableName("user_info")
@Data
public class UserInfoEntity {

    @TableId("user_id")

    private int userId;
    @Basic

    private String userName;

    @Basic

    private String userSexual;
    @Basic

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp userCreateTime;
    @Basic

    private String userPhotoUrl;
    @Basic
    private Object userSign;


}
