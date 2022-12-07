package com.cluboat.springcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyClubDTO {

    private Integer clubId;

    private String clubName;

    private String clubImageUrl;
}
