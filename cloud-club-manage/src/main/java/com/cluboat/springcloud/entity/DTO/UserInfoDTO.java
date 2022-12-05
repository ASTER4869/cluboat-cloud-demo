package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInfoDTO {
    public int userId;
    public String userName;
}