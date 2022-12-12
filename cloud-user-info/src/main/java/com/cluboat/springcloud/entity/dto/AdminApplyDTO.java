package com.cluboat.springcloud.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp adminApplyTime;
}
