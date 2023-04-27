package com.cluboat.springcloud.entity.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class ActivityApplyParam {
    public Integer userId;
    public Integer clubId;
    public String activityApplyReason;
    public String activityTitle;
    public Date activityDate;
    public String activityTime;
    public String activityCampus;
    public String activityLocation;
    public String activityPeopleNum;
    public String activityDescription;

}
