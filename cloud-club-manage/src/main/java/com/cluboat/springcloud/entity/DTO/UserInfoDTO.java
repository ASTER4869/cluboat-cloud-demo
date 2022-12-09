package com.cluboat.springcloud.entity.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInfoDTO {
    public int userId;
    public String userName;
}