package com.cluboat.springcloud.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportAddParam {
    private Integer reporterId;
    private Integer targetId;
    private String targetType;

    private String reportReason;
}
