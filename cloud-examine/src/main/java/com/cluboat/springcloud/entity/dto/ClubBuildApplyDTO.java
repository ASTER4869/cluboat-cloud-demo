package com.cluboat.springcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubBuildApplyDTO {
    private Integer buildApplyId;
    private Integer userId;
    private String buildApplyReason;
    private Timestamp buildApplyTime;
    private String status;
    private String adminClubName;
    private String feedback;
    private String userName;
}
