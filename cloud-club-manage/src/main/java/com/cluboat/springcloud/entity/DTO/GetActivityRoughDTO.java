package com.cluboat.springcloud.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetActivityRoughDTO {
    private Integer activityId;
    private String activityTitle;
    private Date activityDate;
    private String activityCampus;
    private String activityDescription;

}
