package com.cluboat.springcloud.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAdminApplyDTO {
    private Integer adminApplyId;
    private Integer userId;
    private Integer adminClubId;
    private String adminApplyReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp adminApplyTime;
    private String status;
    private String feedback;
    private String userName;
}
