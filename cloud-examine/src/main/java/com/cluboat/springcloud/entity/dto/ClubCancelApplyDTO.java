package com.cluboat.springcloud.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubCancelApplyDTO {
    private Integer cancelApplyId;
    private Integer userId;
    private Integer clubId;
    private String cancelApplyReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp cancelApplyTime;
    private String status;
    private String feedback;
    private String clubName;
    private String userName;
}
