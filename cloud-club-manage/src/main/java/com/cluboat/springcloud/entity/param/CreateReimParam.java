package com.cluboat.springcloud.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReimParam {
    private Integer clubId;
    private Integer userId;
    private String title;
    private BigDecimal amount;
    private String description;

    private List<CreateReimAttachParam> attachments;

}
