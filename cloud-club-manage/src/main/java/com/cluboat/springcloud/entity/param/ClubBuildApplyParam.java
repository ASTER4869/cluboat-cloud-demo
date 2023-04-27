package com.cluboat.springcloud.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ClubBuildApplyParam {
    public int userId;
    public String clubName;
    public String buildApplyReason;
}
