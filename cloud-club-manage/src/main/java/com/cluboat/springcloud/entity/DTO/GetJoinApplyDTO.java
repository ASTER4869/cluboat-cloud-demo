package com.cluboat.springcloud.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetJoinApplyDTO {
    private Integer joinApplyId;
    private Integer userId;
    private Integer joinClubId;
    private String joinApplyContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp joinApplyTime;
    private String status;
    private String feedback;
    private String userName;
}
