package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class JoinApplyParam {
    public int userId;
    public int joinClubId;
    public String joinApplyContent;
    public Timestamp joinApplyTime;
}
