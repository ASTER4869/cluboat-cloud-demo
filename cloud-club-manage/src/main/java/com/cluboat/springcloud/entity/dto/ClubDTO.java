package com.cluboat.springcloud.entity.dto;

import net.sf.jsqlparser.expression.TimestampValue;

import java.sql.Timestamp;

public class ClubDTO {
    private Integer clubId;
    private String clubName;
    private String clubInformation;
    private Timestamp clubCreateTime;
    private String clubImageURL;
}
