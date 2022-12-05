package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdminApplyParam {
    public int userId;
    public int adminClubId;
    public String adminApplyReason;
    public Timestamp adminApplyTime;
}
