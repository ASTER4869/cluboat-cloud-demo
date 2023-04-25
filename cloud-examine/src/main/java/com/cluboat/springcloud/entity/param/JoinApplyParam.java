package com.cluboat.springcloud.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class JoinApplyParam {
    public int userId;
    public int joinClubId;
    public String joinApplyContent;
}
