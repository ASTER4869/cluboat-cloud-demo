package com.cluboat.springcloud.entity.dto;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyActivityDTO {

    private Integer activityId;
    private Integer userId;
    private Integer clubId;
    private String activityTitle;
    private Date activityDate;
    private String activityTime;
    private String activityCampus;
    private String activityLocation;
    private String activityPeopleNum;
    private String activityDescription;

    private String userName;


}
