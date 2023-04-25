package com.cluboat.springcloud.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ClubCancelApplyParam {
    public int userId;
    public int clubId;
    public String cancelApplyReason;
}
