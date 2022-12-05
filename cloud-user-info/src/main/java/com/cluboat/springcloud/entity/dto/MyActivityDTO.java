package com.cluboat.springcloud.entity.dto;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyActivityDTO {
    private Integer activityId;

    private String activityName;

    private Integer clubId;
    private String activityArea;
    private Timestamp activityStartTime;
    private Timestamp activityEndTime;
    private String activityContent;

}
