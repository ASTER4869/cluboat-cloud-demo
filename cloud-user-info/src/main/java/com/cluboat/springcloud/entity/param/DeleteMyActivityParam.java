package com.cluboat.springcloud.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMyActivityParam {
    private Integer userId;
    private Integer activityId;
}
