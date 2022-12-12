package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserClubParam {
    public int userId;
    public int clubId;
    public byte userState;
}