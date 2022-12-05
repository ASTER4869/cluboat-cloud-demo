package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReimApplyParam {
    public int userId;
    public int clubId;
    public String reimApplyReason;
    public Timestamp reimApplyTime;
}
