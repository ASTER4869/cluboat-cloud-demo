package com.cluboat.springcloud.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetActivityApplyDTO {

    private Integer activityApplyId;
    private Integer userId;
    private Integer clubId;
    private String activityApplyReason;
    private String activityTitle;
    private Date activityDate;
    private String activityTime;
    private String activityCampus;
    private String activityLocation;
    private String activityPeopleNum;
    private String activityDescription;

    private String status;
    private String feedback;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp activityApplyTime;

    private String userName;
    private String clubName;
}
