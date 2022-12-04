package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ClubParam {


    public String clubName;

    public String clubInformation;
    public String clubImageUrl;
    public Timestamp clubCreateTime;
}
