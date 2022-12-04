package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityApplyParam {
    public int userId;
    public int clubId;
    public String activityApplyReason;
    public Timestamp activityApplyTime;
}
