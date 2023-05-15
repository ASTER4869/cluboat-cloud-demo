package com.cluboat.springcloud.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostGetByClubIdParam {
    private Integer clubId;
    private String status;
}
