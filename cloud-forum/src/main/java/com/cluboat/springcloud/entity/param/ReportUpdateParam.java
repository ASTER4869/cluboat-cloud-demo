package com.cluboat.springcloud.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportUpdateParam {
    private Integer reportId;

    private String status;
    private String punish;
    private String feedback;
}
