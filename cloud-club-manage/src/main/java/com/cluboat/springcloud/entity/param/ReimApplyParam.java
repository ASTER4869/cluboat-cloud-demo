package com.cluboat.springcloud.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReimApplyParam {
    public int userId;
    public int clubId;
    public String reimApplyReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp reimApplyTime;
}
