package com.cluboat.springcloud.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetActivityDTO {


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
