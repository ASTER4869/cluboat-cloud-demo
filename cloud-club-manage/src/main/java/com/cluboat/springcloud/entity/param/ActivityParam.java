package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityParam {
    public int activityId;
    public int clubId;
    public String activityName;
    public String activityArea;
    public Timestamp activityStartTime;
    public Timestamp activityEndTime;
    public byte activityIsPass;
    public String activityContent;
}
