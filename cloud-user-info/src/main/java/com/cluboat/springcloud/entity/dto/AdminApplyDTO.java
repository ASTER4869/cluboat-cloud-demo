package com.cluboat.springcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminApplyDTO {
    private Integer adminApplyId;
    private Integer userId;
    private Integer adminClubId;
    private String adminApplyReason;
    private Timestamp adminApplyTime;
}
